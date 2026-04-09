package co.edu.uniquindio.poo.proyectoavanzada.Service;

import co.edu.uniquindio.poo.proyectoavanzada.Entity.HistorialSolicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Entity.Solicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Entity.Usuario;
import co.edu.uniquindio.poo.proyectoavanzada.Repository.HistorialSolicitudRepository;
import co.edu.uniquindio.poo.proyectoavanzada.Repository.SolicitudRepository;
import co.edu.uniquindio.poo.proyectoavanzada.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialSolicitudService {

    private final HistorialSolicitudRepository historialRepository;
    private final SolicitudRepository solicitudRepository;
    private final UsuarioRepository usuarioRepository;

    // 🔹 Registrar acción manual (por si el UML lo permite)
    public HistorialSolicitud registrarAccion(
            String solicitudId,
            String usuarioId,
            String accion,
            String observacion
    ){

        Solicitud solicitud = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        HistorialSolicitud historial = new HistorialSolicitud();
        historial.setSolicitud(solicitud);
        historial.setUsuarioResponsable(usuario);
        historial.setAccion(accion);
        historial.setObservacion(observacion);
        historial.setFechaHora(LocalDateTime.now());

        return historialRepository.save(historial);
    }

    // 🔹 Obtener historial por solicitud (clave del UML)
    public List<HistorialSolicitud> obtenerHistorialPorSolicitud(String solicitudId){

        // valida que exista la solicitud
        if(!solicitudRepository.existsById(solicitudId)){
            throw new RuntimeException("La solicitud no existe");
        }

        return historialRepository.findBySolicitudId(solicitudId);
    }

    // 🔹 Obtener todo el historial (admin)
    public List<HistorialSolicitud> listarTodo(){
        return historialRepository.findAll();
    }

    // 🔹 Método interno (importante)
    // Este es el que usan otros services (SolicitudService)
    public void registrarDesdeSistema(
            Solicitud solicitud,
            String accion,
            String observacion
    ){
        HistorialSolicitud historial = new HistorialSolicitud();
        historial.setSolicitud(solicitud);
        historial.setAccion(accion);
        historial.setObservacion(observacion);
        historial.setFechaHora(LocalDateTime.now());

        historialRepository.save(historial);
    }
}
