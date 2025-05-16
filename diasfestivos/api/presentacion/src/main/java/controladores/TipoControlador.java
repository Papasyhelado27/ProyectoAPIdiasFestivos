package diasfestivos.api.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import diasfestivos.api.aplicacion.servicios.TipoServicio;
import diasfestivos.api.dominio.entidades.Tipo;

@RestController
@RequestMapping("/api/tipo")
public class TipoControlador {

    @Autowired
    private TipoServicio tipoServicio;

    @GetMapping("/listar")
    public ResponseEntity<List<Tipo>> listarTipos() {
        return new ResponseEntity<>(tipoServicio.listar(), HttpStatus.OK);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<Tipo> obtenerTipo(@PathVariable int id) {
        Tipo tipo = tipoServicio.obtener(id);
        if (tipo != null) {
            return new ResponseEntity<>(tipo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/agregar")
    public ResponseEntity<Tipo> agregarTipo(@RequestBody Tipo tipo) {
        Tipo nuevoTipo = tipoServicio.agregar(tipo);
        return new ResponseEntity<>(nuevoTipo, HttpStatus.CREATED);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<Tipo> modificarTipo(@PathVariable int id, @RequestBody Tipo tipo) {
        tipo.setId(id);
        Tipo festivoActualizado = tipoServicio.modificar(tipo);
        if (festivoActualizado != null) {
            return new ResponseEntity<>(festivoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarTipo(@PathVariable int id) {
        if (tipoServicio.eliminar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Tipo>> buscarTipos(@RequestParam String nombre) {
        return new ResponseEntity<>(tipoServicio.buscar(nombre), HttpStatus.OK);
    }
}
