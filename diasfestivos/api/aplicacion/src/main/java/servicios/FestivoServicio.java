package diasfestivos.api.aplicacion.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import diasfestivos.api.core.servicios.*;
import diasfestivos.api.dominio.entidades.*;
import diasfestivos.api.infraestructura.repositorios.*;

@Service
public class FestivoServicio implements IFestivoServicio {

    private IFestivoRepositorio repositorio;

    public FestivoServicio(IFestivoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Festivo> listar() {
        return repositorio.findAll();
    }

    @Override
    public Festivo obtener(int id) {
        return repositorio.findById(id).isEmpty() ? null : repositorio.findById(id).get();
    }

    @Override
    public List<Festivo> buscar(String nombre) {
        return repositorio.buscar(nombre);
    }

    @Override
    public Festivo agregar(Festivo festivo) {
        festivo.setId(0);
        return repositorio.save(festivo);
    }

    @Override
    public Festivo modificar(Festivo festivo) {
        if (repositorio.findById(festivo.getId()).isEmpty())
            return null;
        return repositorio.save(festivo);
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

    @Override
    public List<Festivo> buscarPorTipo(int idTipo){
        return repositorio.listarFestivoPorTipo(idTipo);
    }

    @Override
    public List<Festivo> buscarPorDiaMesTipo1(int dia, int mes){
        return repositorio.buscarFestivoTipo1(mes, dia);
    }
    
}
