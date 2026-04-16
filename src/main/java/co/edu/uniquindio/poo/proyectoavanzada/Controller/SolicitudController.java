package co.edu.uniquindio.poo.proyectoavanzada.Controller;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Solicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.EstadoSolicitud;
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

    @PostMapping
    public ResponseEntity<Solicitud> registrarSolicitud(
            @RequestParam String descripcion,
            @RequestParam String canalOrigen,
            @RequestParam String solicitanteId
    ) {
        return ResponseEntity.status(201).body(
                solicitudService.registrarSolicitud(descripcion, canalOrigen, solicitanteId)
        );
    }

    @PatchMapping("/{id}/asignar")
    public ResponseEntity<Solicitud> asignarResponsable(
            @PathVariable String id,
            @RequestParam String responsableId
    ){
        return ResponseEntity.ok(
                solicitudService.asignarResponsable(id, responsableId)
        );
    }

    @PatchMapping("/{id}/clasificar")
    public ResponseEntity<Solicitud> clasificar(@PathVariable String id){
        return ResponseEntity.ok(
                solicitudService.clasificarSolicitud(id)
        );
    }

    @PatchMapping("/{id}/iniciar-atencion")
    public ResponseEntity<Solicitud> iniciar(@PathVariable String id){
        return ResponseEntity.ok(
                solicitudService.iniciarAtencion(id)
        );
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<Solicitud> finalizar(@PathVariable String id){
        return ResponseEntity.ok(
                solicitudService.finalizarSolicitud(id)
        );
    }

    @PatchMapping("/{id}/cerrar")
    public ResponseEntity<Solicitud> cerrar(
            @PathVariable String id,
            @RequestParam String observacion
    ){
        return ResponseEntity.ok(
                solicitudService.cerrarSolicitud(id, observacion)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> obtener(@PathVariable String id){
        return ResponseEntity.ok(
                solicitudService.obtenerPorId(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<Solicitud>> listar(){
        return ResponseEntity.ok(
                solicitudService.listar()
        );
    }
}
