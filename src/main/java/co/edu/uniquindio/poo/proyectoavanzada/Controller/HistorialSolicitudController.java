package co.edu.uniquindio.poo.proyectoavanzada.Controller;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.HistorialSolicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Service.HistorialSolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial")
@RequiredArgsConstructor
public class HistorialSolicitudController {

    private final HistorialSolicitudService historialSolicitudService;

    // 🔹 Obtener historial por solicitud
    @GetMapping("/solicitud/{solicitudId}")
    public ResponseEntity<List<HistorialSolicitud>> obtenerHistorialPorSolicitud(
            @PathVariable String solicitudId
    ){
        return ResponseEntity.ok(
                historialSolicitudService.obtenerHistorialPorSolicitud(solicitudId));
    }

    // 🔹 Listar todo el historial
    @GetMapping
    public ResponseEntity<List<HistorialSolicitud>> listarTodo(){
        return ResponseEntity.ok(
                historialSolicitudService.listarTodo());
    }
}
