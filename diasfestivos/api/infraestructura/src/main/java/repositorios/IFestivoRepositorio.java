package diasfestivos.api.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import diasfestivos.api.dominio.entidades.*;

@Repository
public interface IFestivoRepositorio extends JpaRepository<Festivo, Integer> {

    @Query("SELECT f FROM Festivo f WHERE f.nombre LIKE '%' || ?1 || '%'")
    public List<Festivo> buscar(String nombre);

    // Seleccionar festivo basado en dia y mes para festivos cuya fecha no cambia
    @Query("SELECT f FROM Festivo f " +
        "WHERE f.clasificacionFestivo.id = 1 " +
        "AND f.mes = ?1 " +
        "AND f.dia = ?2")
    public List<Festivo> buscarFestivoTipo1(int mes, int dia);

    // Obtener festivo por tipo
    @Query("SELECT f FROM Festivo f " +
        "WHERE f.clasificacionFestivo.id = ?1")
    public List<Festivo> listarFestivoPorTipo(int idTipo);
}
