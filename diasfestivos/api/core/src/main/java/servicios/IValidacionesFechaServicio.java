// Interfaz que define las operaciones a realizar sobre las fechas

package diasfestivos.api.core.servicios;

import java.util.Date;

public interface IValidacionesFechaServicio {

    public boolean validarPorFestivoTipo1(int año, int mes, int dia);

    public boolean validarPorFestivoTipo2(int año, int mes, int dia);

    public boolean validarPorFestivoTipo3(int año, int mes, int dia);

    public boolean validarPorFestivoTipo4(int año, int mes, int dia);

    public boolean validarFechaFestivo(int año, int mes, int dia);

    public String validarSiEsFestivo(int año, int mes, int dia);

    public String validarSiEsFechaValida(int año, int mes, int dia);

}
