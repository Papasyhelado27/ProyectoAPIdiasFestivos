package diasfestivos.api.aplicacion.servicios;

import java.util.List;
import java.util.Date;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import diasfestivos.api.core.servicios.*;
import diasfestivos.api.core.utils.*;
import diasfestivos.api.dominio.entidades.*;
import diasfestivos.api.infraestructura.repositorios.*;


@Service
public class ValidacionesFechasServicio implements IValidacionesFechaServicio {

    @Autowired
    private IFestivoServicio festivoServicio;

    public ValidacionesFechasServicio(IFestivoServicio festivoServicio) {
        this.festivoServicio = festivoServicio;
    }

    public boolean validarPorFestivoTipo1(int año, int mes, int dia){

        return !festivoServicio.buscarPorDiaMesTipo1(dia, mes).isEmpty();

    }

    public boolean validarPorFestivoTipo2(int año, int mes, int dia){

        Date determinarFestivo = new Date(año - 1900, mes - 1, dia);

        List<Festivo> festivosTipo2 =  festivoServicio.buscarPorTipo(2);

        Date siguienteLunes;

        for(Festivo festivo : festivosTipo2) {
            siguienteLunes = IOperacionesFechasUtils.obtenerSiguienteLunes(año, festivo.getMes(), festivo.getDia());
            
            if(determinarFestivo.equals(siguienteLunes))
                return true;
        }

        return false;

    }

    public boolean validarPorFestivoTipo3(int año, int mes, int dia){

        List<Festivo> festivosTipo3 =  festivoServicio.buscarPorTipo(3);

        Date fechaConsulta = new Date(año - 1900, mes - 1, dia);

        Date domingoPascua = IOperacionesFechasUtils.obtenerFechaDomingoPascua(año);

        for (Festivo festivo : festivosTipo3) {

            Calendar calendarioFestivo = Calendar.getInstance();
            calendarioFestivo.setTime(domingoPascua);
            Date fechaFestivo = calendarioFestivo.getTime();

            fechaFestivo = IOperacionesFechasUtils.agregarDias(fechaFestivo, festivo.getDiasPascua());

            if(fechaConsulta.equals(fechaFestivo))
                return true;
        }

        return false;
    }

    public boolean validarPorFestivoTipo4(int año, int mes, int dia){

        List<Festivo> festivosTipo4 =  festivoServicio.buscarPorTipo(4);
        Date fechaConsulta = new Date(año - 1900, mes - 1, dia);

        Date domingoPascua = IOperacionesFechasUtils.obtenerFechaDomingoPascua(año);

        for (Festivo festivo : festivosTipo4) {

            Calendar calendarioFestivo = Calendar.getInstance();
            calendarioFestivo.setTime(domingoPascua);
            Date fechaFestivo = calendarioFestivo.getTime();

            fechaFestivo = IOperacionesFechasUtils.agregarDias(fechaFestivo, festivo.getDiasPascua());

            calendarioFestivo.setTime(fechaFestivo);

            fechaFestivo = IOperacionesFechasUtils.obtenerSiguienteLunes(calendarioFestivo.get(Calendar.YEAR), calendarioFestivo.get(Calendar.MONTH) + 1, calendarioFestivo.get(Calendar.DAY_OF_MONTH));

            if(fechaConsulta.equals(fechaFestivo))
                return true;
        }
        return false;

    }

    public boolean validarFechaFestivo(int año, int mes, int dia){
        
        // Si devuelve algo al buscar el Festivo, entonces es festivo
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

    public String validarSiEsFestivo(int año, int mes, int dia){
        if(validarFechaFestivo(año, mes, dia))
            return "Es festivo";
        else
            return "No es festivo";
    }

    public String validarSiEsFechaValida(int año, int mes, int dia) {
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        try {
            calendar.set(año, mes, dia);
            calendar.getTime();
            return validarSiEsFestivo(año, mes, dia);
        } catch (Exception e) {
            return "Fecha no válida";
        }
    }
    
}