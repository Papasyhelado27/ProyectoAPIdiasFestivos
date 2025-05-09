// Interfaz que define las operaciones a realizar sobre las fechas

package diasfestivos.api.core.servicios;

import java.util.Date;

public interface IOperacionesFechasServicio {

    public Date agregarDias(Date fecha, int dias);
    
    public Date obtenerSiguienteLunes(Date fecha);

    public Date obtenerDomingoRamos(int año);

    public Date obtenerFechaDomingoPascua(int año);

    public boolean validarPorFestivoTipo1(int año, int mes, int dia);

    public boolean validarPorFestivoTipo2(int año, int mes, int dia);

    public boolean validarPorFestivoTipo3(int año, int mes, int dia);

    public boolean validarPorFestivoTipo4(int año, int mes, int dia);

    public boolean validarFechaFestivo(int año, int mes, int dia);

    public String esFestivo(int año, int mes, int dia);

}
