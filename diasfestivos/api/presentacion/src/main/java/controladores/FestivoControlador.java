package diasfestivos.api.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import diasfestivos.api.aplicacion.servicios.FestivoServicio;
import diasfestivos.api.dominio.entidades.Festivo;

@RestController
@RequestMapping("/api/festivos")
public class FestivoControlador {

    @Autowired
    private FestivoServicio festivoServicio;

    @GetMapping("/listar")
    public ResponseEntity<List<Festivo>> listarFestivos() {
        return new ResponseEntity<>(festivoServicio.listar(), HttpStatus.OK);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<Festivo> obtenerFestivo(@PathVariable int id) {
        Festivo festivo = festivoServicio.obtener(id);
        if (festivo != null) {
            return new ResponseEntity<>(festivo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/agregar")
    public ResponseEntity<Festivo> agregarFestivo(@RequestBody Festivo festivo) {
        Festivo nuevoFestivo = festivoServicio.agregar(festivo);
        return new ResponseEntity<>(nuevoFestivo, HttpStatus.CREATED);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<Festivo> modificarFestivo(@PathVariable int id, @RequestBody Festivo festivo) {
        festivo.setId(id);
        Festivo festivoActualizado = festivoServicio.modificar(festivo);
        if (festivoActualizado != null) {
            return new ResponseEntity<>(festivoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarFestivo(@PathVariable int id) {
        if (festivoServicio.eliminar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Festivo>> buscarFestivos(@RequestParam String nombre) {
        return new ResponseEntity<>(festivoServicio.buscar(nombre), HttpStatus.OK);
    }

    @GetMapping("/tipo/{idTipo}")
    public ResponseEntity<List<Festivo>> listarFestivosPorTipo(@PathVariable int idTipo) {
        return new ResponseEntity<>(festivoServicio.buscarPorTipo(idTipo), HttpStatus.OK);
    }
}
