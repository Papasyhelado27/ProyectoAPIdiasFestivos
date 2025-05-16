package diasfestivos.api.core.utils;

import java.util.Date;
import java.util.Calendar;

public interface IOperacionesFechasUtils {

    public static Date agregarDias(Date fecha, int dias){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DATE, dias);
        return calendario.getTime();
    }
    
    public static Date obtenerSiguienteLunes(int año, int mes, int dia){
        
        Date fecha = new Date(año - 1900, mes - 1, dia);

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
    public static Date obtenerDomingoRamos(int año){
        int a = año % 19;
        int b = año % 4;
        int c = año % 7;
        int d = (19 * a + 24) % 30;

        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

        int dia = 15 + dias;
        int mes = 3;

        return new Date(año - 1900, mes - 1, dia);

    }

    public static Date obtenerFechaDomingoPascua(int año){
        return agregarDias(obtenerDomingoRamos(año), 7);
    }
    
}