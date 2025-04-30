package diasfestivos.api.core.servicios;

import java.util.List;
import diasfestivos.api.dominio.entidades.*;

public interface IFestivoServicio {

    public List<Festivo> listar();

    public Festivo obtener(int id);

    public List<Festivo> buscar(String nombre);

    public Festivo agregar(Festivo festivo);

    public Festivo modificar(Festivo festivo);

    public boolean eliminar(int id);

}
