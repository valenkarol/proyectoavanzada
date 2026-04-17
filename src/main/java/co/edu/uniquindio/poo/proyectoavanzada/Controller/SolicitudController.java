package co.edu.uniquindio.poo.proyectoavanzada.Controller;

import co.edu.uniquindio.poo.proyectoavanzada.DTO.CerrarSolicitudRequest;
import co.edu.uniquindio.poo.proyectoavanzada.DTO.SolicitudCreateRequest;
import co.edu.uniquindio.poo.proyectoavanzada.DTO.SolicitudResponse;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.HistorialSolicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Service.HistorialSolicitudService;
import co.edu.uniquindio.poo.proyectoavanzada.Service.SolicitudService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final SolicitudService solicitudService;
    private final HistorialSolicitudService historialSolicitudService;

    @PostMapping
    public ResponseEntity<SolicitudResponse> registrarSolicitud(
            @RequestBody @Valid SolicitudCreateRequest request
    ) {
        return ResponseEntity.status(201).body(
                SolicitudMapper.toResponse(
                        solicitudService.registrarSolicitud(
                                request.getDescripcion(),
                                request.getCanalOrigen().name(),
                                request.getSolicitanteId()
                        )
                )
        );
    }

    @PatchMapping("/{id}/asignar")
    public ResponseEntity<SolicitudResponse> asignarResponsable(
            @PathVariable String id,
            @RequestParam String responsableId
    ){
        return ResponseEntity.ok(
                SolicitudMapper.toResponse(
                        solicitudService.asignarResponsable(id, responsableId)
                )
        );
    }

    @PatchMapping("/{id}/clasificar")
    public ResponseEntity<SolicitudResponse> clasificar(@PathVariable String id){
        return ResponseEntity.ok(
                SolicitudMapper.toResponse(
                        solicitudService.clasificarSolicitud(id)
                )
        );
    }

    @PatchMapping("/{id}/iniciar-atencion")
    public ResponseEntity<SolicitudResponse> iniciar(@PathVariable String id){
        return ResponseEntity.ok(
                SolicitudMapper.toResponse(
                    solicitudService.iniciarAtencion(id)
                )
        );
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<SolicitudResponse> finalizar(@PathVariable String id){
        return ResponseEntity.ok(
                SolicitudMapper.toResponse(
                    solicitudService.finalizarSolicitud(id)
                )
        );
    }

    @PatchMapping("/{id}/cerrar")
    public ResponseEntity<SolicitudResponse> cerrar(
            @PathVariable String id,
            @RequestBody @Valid CerrarSolicitudRequest request
    ){
        return ResponseEntity.ok(
                SolicitudMapper.toResponse(
                        solicitudService.cerrarSolicitud(id, request.getObservacion())
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudResponse> obtener(@PathVariable String id){
        return ResponseEntity.ok(
                SolicitudMapper.toResponse(
                    solicitudService.obtenerPorId(id)
                )
        );
    }

    @GetMapping("/{id}/historial")
    public ResponseEntity<List<HistorialSolicitud>> historial(@PathVariable String id){
        return ResponseEntity.ok(
                historialSolicitudService.obtenerHistorialPorSolicitud(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<SolicitudResponse>> listar(){
        return ResponseEntity.ok(
                solicitudService.listar()
                        .stream()
                        .map(SolicitudMapper::toResponse)
                        .toList()
        );
    }
}
