// Interfaz que define las operaciones a realizar sobre las fechas

package diasfestivos.api.core.servicios;

import java.util.Date;

public interface IOperacionesFechasServicio {

    public Date agregarDias();
    
    public Date obtenerSiguienteLunes();

    public Date obtenerDomingoRamos();

    public Date obtenerFechaDomingoPascua();

}
