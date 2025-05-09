package diasfestivos.api.aplicacion.servicios;

import java.util.List;
import java.util.Date;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import diasfestivos.api.core.servicios.*;
import diasfestivos.api.dominio.entidades.*;
import diasfestivos.api.infraestructura.repositorios.*;

@Service
public class OperacionesFechasServicio implements IOperacionesFechasServicio {

    private IFestivoServicio festivoServicio;

    public OperacionesFechasServicio(IFestivoServicio festivoServicio) {
        this.festivoServicio = festivoServicio;
    }

    public Date agregarDias(Date fecha, int dias){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DATE, dias);
        return calendario.getTime();
    }
    
    public Date obtenerSiguienteLunes(Date fecha){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);

        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
        if(diaSemana != Calendar.MONDAY) {
            if (diaSemana > Calendar.MONDAY)
                fecha = agregarDias(fecha, 9 - diaSemana);
            else
                fecha = agregarDias(fecha, 1);
        }

        return fecha;

    }

    // obtener inicio de Semana Sante
    public Date obtenerDomingoRamos(int año){
        int a = año % 19;
        int b = año % 4;
        int c = año % 7;
        int d = (19 * a + 24) % 30;

        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

        int dia = 15 + dias;
        int mes = 3;

        return new Date(año - 1900, mes - 1, dia);

    }

    public Date obtenerFechaDomingoPascua(int año){ 

        return obtenerDomingoRamos(año);

    }

    public boolean validarPorFestivoTipo1(int año, int mes, int dia){

        return !festivoServicio.buscarPorDiaMesTipo1(dia, mes).isEmpty();

    }

    public boolean validarPorFestivoTipo2(int año, int mes, int dia){

        List<Festivo> festivosTipo2 =  festivoServicio.buscarPorTipo(2);

        Date siguienteLunes;

        for(Festivo festivo : festivosTipo2) {
            Date fechaFestivo = new Date(año - 1900, festivo.getMes(), festivo.getDia());
            siguienteLunes = obtenerSiguienteLunes(fechaFestivo);
            
            if(fechaFestivo.equals(siguienteLunes))
                return true;
        }

        return false;

    }

    public boolean validarPorFestivoTipo3(int año, int mes, int dia){

        List<Festivo> festivosTipo3 =  festivoServicio.buscarPorTipo(3);

        return false;

    }

    public boolean validarPorFestivoTipo4(int año, int mes, int dia){

        List<Festivo> festivosTipo4 =  festivoServicio.buscarPorTipo(4);

        return false;

    }

    public boolean validarFechaFestivo(int año, int mes, int dia){
        
        // Si devuelve algo al buscar el Festivo, entonces es festivo
        if(validarPorFestivoTipo1(año, mes, dia)) {
            return true;
        } else if (validarPorFestivoTipo2(año, mes, dia)) { // Si no devuelve nada, validar que es tipo 2
            return true;
        } else if (validarPorFestivoTipo3(año, mes, dia)) {
            return true;
        } else if (validarPorFestivoTipo4(año, mes, dia)) {
            return true;
        }

        return false;

    }

    
}
