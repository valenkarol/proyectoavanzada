package co.edu.uniquindio.poo.proyectoavanzada.Controller;

import co.edu.uniquindio.poo.proyectoavanzada.Entity.HistorialSolicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Service.HistorialSolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//-----------------------------------------
//NO SABEMOS SI ESTE VA - PREGUNTAAAAAAAR - NO VA ESTE CONTROLLER PORQUE ES PROPIO DE APP
//-----------------------------------------

@RestController
@RequestMapping("/historial")
@RequiredArgsConstructor
public class HistorialSolicitudController {

    private final HistorialSolicitudService historialService;

    // 🔹 Registrar acción manual
    @PostMapping("/registrar")
    public ResponseEntity<HistorialSolicitud> registrarAccion(
            @RequestParam String solicitudId,
            @RequestParam String usuarioId,
            @RequestParam String accion,
            @RequestParam String observacion
    ){
        return ResponseEntity.ok(
                historialService.registrarAccion(
                        solicitudId,
                        usuarioId,
                        accion,
                        observacion
                ));
    }

    // 🔹 Obtener historial por solicitud
    @GetMapping("/solicitud/{solicitudId}")
    public ResponseEntity<List<HistorialSolicitud>> obtenerHistorialPorSolicitud(
            @PathVariable String solicitudId
    ){
        return ResponseEntity.ok(
                historialService.obtenerHistorialPorSolicitud(solicitudId));
    }

    // 🔹 Listar todo el historial
    @GetMapping
    public ResponseEntity<List<HistorialSolicitud>> listarTodo(){
        return ResponseEntity.ok(
                historialService.listarTodo());
    }
}
