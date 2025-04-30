package diasfestivos.api.core.servicios;

import java.util.List;
import diasfestivos.api.dominio.entidades.*;

public interface ITipoServicio {

    public List<Tipo> listar();

    public Tipo obtener(int id);

    public List<Tipo> buscar(String nombre);

    public Tipo agregar(Tipo tipo);

    public Tipo modificar(Tipo tipo);

    public boolean eliminar(int id);

}
