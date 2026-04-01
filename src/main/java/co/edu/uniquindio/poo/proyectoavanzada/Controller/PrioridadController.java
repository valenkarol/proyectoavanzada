package co.edu.uniquindio.poo.proyectoavanzada.Controller;

import co.edu.uniquindio.poo.proyectoavanzada.Entity.Prioridad;
import co.edu.uniquindio.poo.proyectoavanzada.Entity.Solicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Service.PrioridadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
//---------------------------------------------------
//PREGUNTAR SI ESTE CONTROLLER SI VA
//--------------------------------------------------
@RestController
@RequestMapping("/prioridades")
@RequiredArgsConstructor
public class PrioridadController {

    private final PrioridadService prioridadService;

    // 🔹 Crear prioridad
    @PostMapping
    public ResponseEntity<Prioridad> crearPrioridad(
            @RequestParam String nivel,
            @RequestParam String fechaLimite,
            @RequestParam String justificacion
    ){
        return ResponseEntity.ok(
                prioridadService.crearPrioridad(
                        nivel,
                        LocalDateTime.parse(fechaLimite),
                        justificacion
                )
        );
    }

    // 🔹 Asignar prioridad a solicitud
    @PutMapping("/asignar")
    public ResponseEntity<Solicitud> asignarPrioridadASolicitud(
            @RequestParam String solicitudId,
            @RequestParam String prioridadId
    ){
        return ResponseEntity.ok(
                prioridadService.asignarPrioridadASolicitud(solicitudId, prioridadId)
        );
    }

    // 🔹 Obtener prioridad por id
    @GetMapping("/{id}")
    public ResponseEntity<Prioridad> obtenerPorId(@PathVariable String id){
        return ResponseEntity.ok(
                prioridadService.obtenerPorId(id)
        );
    }

    // 🔹 Listar prioridades
    @GetMapping
    public ResponseEntity<List<Prioridad>> listar(){
        return ResponseEntity.ok(prioridadService.listar());
    }

    // 🔹 Actualizar prioridad
    @PutMapping("/{id}")
    public ResponseEntity<Prioridad> actualizar(
            @PathVariable String id,
            @RequestBody Prioridad prioridad
    ){
        return ResponseEntity.ok(
                prioridadService.actualizar(id, prioridad)
        );
    }

    // 🔹 Eliminar prioridad
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id){
        prioridadService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
