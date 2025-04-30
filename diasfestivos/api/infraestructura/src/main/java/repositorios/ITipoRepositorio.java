package diasfestivos.api.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import diasfestivos.api.dominio.entidades.*;

public interface ITipoRepositorio {

    // Buscar tipo basado en id
    @Query("SELECT t FROM Tipo t  WHERE t.id = ?1")
    public List<Tipo> buscarTipo(int id);

}
