package co.edu.uniquindio.poo.proyectoavanzada.Controller;

import co.edu.uniquindio.poo.proyectoavanzada.Entity.Solicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Enum.EstadoSolicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Service.SolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final SolicitudService solicitudService;

    // 🔹 Crear solicitud
    @PostMapping("/crear")
    public ResponseEntity<Solicitud> crearSolicitud(
            @RequestParam String descripcion,
            @RequestParam String canalOrigen,
            @RequestParam String solicitanteId
    ){
        return ResponseEntity.ok(
                solicitudService.crearSolicitud(descripcion, canalOrigen, solicitanteId)
        );
    }

    // 🔹 Asignar responsable
    @PutMapping("/{id}/asignar-responsable")
    public ResponseEntity<Solicitud> asignarResponsable(
            @PathVariable String id,
            @RequestParam String responsableId
    ){
        return ResponseEntity.ok(
                solicitudService.asignarResponsable(id, responsableId)
        );
    }

    // 🔹 Cambiar estado
    @PutMapping("/{id}/estado")
    public ResponseEntity<Solicitud> cambiarEstado(
            @PathVariable String id,
            @RequestParam EstadoSolicitud estado
    ){
        return ResponseEntity.ok(
                solicitudService.cambiarEstado(id, estado)
        );
    }

    // 🔹 Asignar prioridad
    @PutMapping("/{id}/prioridad")
    public ResponseEntity<Solicitud> asignarPrioridad(
            @PathVariable String id,
            @RequestParam String prioridadId
    ){
        return ResponseEntity.ok(
                solicitudService.asignarPrioridad(id, prioridadId)
        );
    }

    // 🔹 Cerrar solicitud
    @PutMapping("/{id}/cerrar")
    public ResponseEntity<Solicitud> cerrarSolicitud(
            @PathVariable String id
    ){
        return ResponseEntity.ok(
                solicitudService.cerrarSolicitud(id)
        );
    }

    // 🔹 Obtener solicitud
    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> obtenerPorId(@PathVariable String id){
        return ResponseEntity.ok(
                solicitudService.obtenerPorId(id)
        );
    }

    // 🔹 Listar solicitudes
    @GetMapping
    public ResponseEntity<List<Solicitud>> listar(){
        return ResponseEntity.ok(
                solicitudService.listar()
        );
    }
}
