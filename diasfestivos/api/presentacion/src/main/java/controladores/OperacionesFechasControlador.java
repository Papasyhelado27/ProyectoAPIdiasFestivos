package diasfestivos.api.presentacion.controladores;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import diasfestivos.api.dominio.entidades.*;
import diasfestivos.api.core.servicios.*;

@RestController
@RequestMapping("/festivos")
public class OperacionesFechasControlador {

    private IOperacionesFechasServicio servicio;

    public OperacionesFechasControlador(IOperacionesFechasServicio servicio) {
        this.servicio = servicio;
    }

    @RequestMapping(value = "/verificar/{año}/{mes}/{dia}", method = RequestMethod.GET)
    public String verificar(@PathVariable int año, @PathVariable int mes, @PathVariable int dia) {
        return servicio.esFechaValida(año, mes, dia);
    }

}