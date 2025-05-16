package diasfestivos.api.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import diasfestivos.api.dominio.entidades.*;

@Repository
public interface ITipoRepositorio extends JpaRepository<Tipo, Integer> {

    @Query("SELECT t FROM Tipo t WHERE t.estandar ILIKE '%' || ?1 || '%'")
    public List<Tipo> buscar(String tipo);


    // Buscar tipo basado en id // Es lo mismo que find by id entonces no importa
    // @Query("SELECT t FROM Tipo t  WHERE t.id = ?1")
    // public List<Tipo> buscarPorId(int id);

}
