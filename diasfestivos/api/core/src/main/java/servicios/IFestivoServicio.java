package diasfestivos.api.core.servicios;

import java.sql.Date;
import java.util.List;
import diasfestivos.api.dominio.entidades.*;

import java.util.Calendar;

public interface IFestivoServicio {

    // Métodos CRUD

    public List<Festivo> listar();

    public Festivo obtener(int id);

    public List<Festivo> buscar(String nombre);

    public Festivo agregar(Festivo festivo);

    public Festivo modificar(Festivo festivo);

    public boolean eliminar(int id);

    // Metodos específicos de la solución

    public List<Festivo> buscarPorTipo(int idTipo);

    public List<Festivo> buscarPorDiaMesTipo1(int dia, int mes);

}
