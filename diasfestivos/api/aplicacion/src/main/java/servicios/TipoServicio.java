package diasfestivos.api.aplicacion.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import diasfestivos.api.core.servicios.*;
import diasfestivos.api.dominio.entidades.*;
import diasfestivos.api.infraestructura.repositorios.*;

@Service
public class TipoServicio implements ITipoServicio {

    @Autowired
    private ITipoRepositorio repositorio;

    public TipoServicio(ITipoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Tipo> listar() {
        return repositorio.findAll();
    }

    @Override
    public Tipo obtener(int id) {
        return repositorio.findById(id).isEmpty() ? null : repositorio.findById(id).get();
    }

    @Override
    public List<Tipo> buscar(String nombre) {
        return repositorio.buscar(nombre);
    }

    @Override
    public Tipo agregar(Tipo tipo) {
        tipo.setId(0);
        return repositorio.save(tipo);
    }

    @Override
    public Tipo modificar(Tipo tipo) {
        if (repositorio.findById(tipo.getId()).isEmpty())
            return null;
        return repositorio.save(tipo);
    }

    @Override
    public boolean eliminar(int id) {
        try {
            repositorio.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
