package diasfestivos.api.aplicacion.servicios;

import java.util.List;
import java.util.Date;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import diasfestivos.api.core.servicios.*;
import diasfestivos.api.dominio.entidades.*;
import diasfestivos.api.infraestructura.repositorios.*;

@Service
public class OperacionesFechasServicio implements IOperacionesFechasServicio {

    @Autowired
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

    
    public Date obtenerSiguienteLunes(int año, int mes, int dia){
        
        Date fecha = new Date(año - 1900, mes - 1, dia);

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        
        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
        if(diaSemana != Calendar.MONDAY) {
            if (diaSemana > Calendar.MONDAY)
                calendario.add(Calendar.DATE, 9 - diaSemana);
            else
                calendario.add(Calendar.DATE, Calendar.MONDAY - diaSemana);
        }

        return calendario.getTime();
    }

    // obtener inicio de Semana Sante
    public Date obtenerDomingoRamos(int año){
        int a = año % 19;
        int b = año % 4;
        int c = año % 7;
        int d = (19 * a + 24) % 30;

        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

        Calendar calendario = Calendar.getInstance();
        calendario.set(año, Calendar.MARCH, 15);
        calendario.add(Calendar.DATE, dias);

        return calendario.getTime();
    }

    public Date obtenerFechaDomingoPascua(int año){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(obtenerDomingoRamos(año));
        calendario.add(Calendar.DATE, 7);
        return calendario.getTime();
    }

    public boolean validarPorFestivoTipo1(int año, int mes, int dia){
        return !festivoServicio.buscarPorDiaMesTipo1(dia, mes).isEmpty();
    }

    public boolean validarPorFestivoTipo2(int año, int mes, int dia){

        Date determinarFestivo = new Date(año - 1900, mes - 1, dia);

        List<Festivo> festivosTipo2 =  festivoServicio.buscarPorTipo(2);
        Calendar calendarioFechaConsulta = Calendar.getInstance();
        calendarioFechaConsulta.set(año, mes - 1, dia);
        Date fechaConsulta = calendarioFechaConsulta.getTime();

        for(Festivo festivo : festivosTipo2) {
            siguienteLunes = obtenerSiguienteLunes(año, festivo.getMes(), festivo.getDia());
            
            if(determinarFestivo.equals(siguienteLunes))
                return true;
            }
        }
        return false;
    }

    public boolean validarPorFestivoTipo3(int año, int mes, int dia){
        List<Festivo> festivosTipo3 =  festivoServicio.buscarPorTipo(3);
        Calendar calendarioFechaConsulta = Calendar.getInstance();
        calendarioFechaConsulta.set(año, mes - 1, dia);
        Date fechaConsulta = calendarioFechaConsulta.getTime();

        Date domingoPascua = obtenerFechaDomingoPascua(año);
        Calendar calendarioPascua = Calendar.getInstance();
        calendarioPascua.setTime(domingoPascua);

        for (Festivo festivo : festivosTipo3) {
            Calendar calendarioFestivo = Calendar.getInstance();
            calendarioFestivo.setTime(domingoPascua);
            calendarioFestivo.add(Calendar.DATE, festivo.getEasterOffset());
            if (calendarioFechaConsulta.get(Calendar.YEAR) == calendarioFestivo.get(Calendar.YEAR) &&
                    calendarioFechaConsulta.get(Calendar.MONTH) == calendarioFestivo.get(Calendar.MONTH) &&
                    calendarioFechaConsulta.get(Calendar.DAY_OF_MONTH) == calendarioFestivo.get(Calendar.DAY_OF_MONTH)) {
                return true;
            }
        }
        return false;
    }

    public boolean validarPorFestivoTipo4(int año, int mes, int dia){
        List<Festivo> festivosTipo4 =  festivoServicio.buscarPorTipo(4);
        Calendar calendarioFechaConsulta = Calendar.getInstance();
        calendarioFechaConsulta.set(año, mes - 1, dia);
        Date fechaConsulta = calendarioFechaConsulta.getTime();

        Date domingoPascua = obtenerFechaDomingoPascua(año);

        for (Festivo festivo : festivosTipo4) {
            Calendar calendarioFestivo = Calendar.getInstance();
            calendarioFestivo.setTime(domingoPascua);
            calendarioFestivo.add(Calendar.DATE, festivo.getEasterOffset());
            Date fechaFestivoOriginal = calendarioFestivo.getTime();
            Date siguienteLunes = obtenerSiguienteLunes(fechaFestivoOriginal);

            if (fechaConsulta.equals(siguienteLunes)) {
                return true;
            }
        }
        return false;
    }

    public boolean validarFechaFestivo(int año, int mes, int dia){

        // Si devuelve algo al buscar Festivo, entonces es festivo
        if(validarPorFestivoTipo1(año, mes, dia)) {
            System.out.println("Festivo tipo 1");
            return true;
        } else if (validarPorFestivoTipo2(año, mes, dia)) { // Si no devuelve nada, validar que es tipo 2
            System.out.println("Festivo tipo 2");
            return true;
        } else if (validarPorFestivoTipo3(año, mes, dia)) {
            System.out.println("Festivo tipo 3");
            return true;
        } else if (validarPorFestivoTipo4(año, mes, dia)) {
            System.out.println("Festivo tipo 4");
            return true;
        }

        return false;
    }

    public String esFestivo(int año, int mes, int dia){
        if(validarFechaFestivo(año, mes, dia))
            return "Es festivo";
        else
            return "No es festivo";
    }

    
}
