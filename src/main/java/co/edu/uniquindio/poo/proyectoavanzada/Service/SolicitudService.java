package co.edu.uniquindio.poo.proyectoavanzada.Service;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Solicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Usuario;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.EstadoSolicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.CanalOrigen;
import co.edu.uniquindio.poo.proyectoavanzada.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final UsuarioRepository usuarioRepository;
    private final HistorialSolicitudService historialService;

    // 🔹 Crear solicitud
    public Solicitud crearSolicitud(
            String descripcion,
            String canalOrigen,
            String solicitanteId
    ){

        Usuario solicitante = usuarioRepository.findById(solicitanteId)
                .orElseThrow(() -> new RuntimeException("Solicitante no existe"));

        if(descripcion == null || descripcion.isBlank()){
            throw new RuntimeException("La descripción es obligatoria");
        }

        Solicitud solicitud = new Solicitud();
        solicitud.setDescripcion(descripcion);
        solicitud.setFechaRegistro(LocalDateTime.now());
        solicitud.setEstado(EstadoSolicitud.REGISTRADA);
        solicitud.setCanalOrigen(CanalOrigen.valueOf(canalOrigen));
        solicitud.setSolicitante(solicitante);

        Solicitud guardada = solicitudRepository.save(solicitud);

        historialService.registrarDesdeSistema(
                guardada,
                "CREACION",
                "Solicitud creada"
        );

        return guardada;
    }

    // 🔹 Asignar responsable
    public Solicitud asignarResponsable(String solicitudId, String responsableId){

        Solicitud solicitud = obtenerPorId(solicitudId);

        Usuario responsable = usuarioRepository.findById(responsableId)
                .orElseThrow(() -> new RuntimeException("Responsable no existe"));

        if(!responsable.isActivo()){
            throw new RuntimeException("El responsable está inactivo");
        }

        solicitud.setResponsable(responsable);
        solicitud.setEstado(EstadoSolicitud.EN_ATENCION);

        Solicitud actualizada = solicitudRepository.save(solicitud);

        historialService.registrarDesdeSistema(
                actualizada,
                "ASIGNACION",
                "Responsable asignado"
        );

        return actualizada;
    }

    // 🔹 Cambiar estado
    public Solicitud cambiarEstado(String solicitudId, EstadoSolicitud nuevoEstado){

        Solicitud solicitud = obtenerPorId(solicitudId);

        // Validación simple de flujo
        if(solicitud.getEstado() == EstadoSolicitud.CERRADA){
            throw new RuntimeException("No se puede modificar una solicitud cerrada");
        }

        solicitud.setEstado(nuevoEstado);

        Solicitud actualizada = solicitudRepository.save(solicitud);

        historialService.registrarDesdeSistema(
                actualizada,
                "CAMBIO_ESTADO",
                nuevoEstado.name()
        );

        return actualizada;
    }

    /** 🔹 Asignar prioridad
    public Solicitud asignarPrioridad(String solicitudId, String prioridadId){

        Solicitud solicitud = obtenerPorId(solicitudId);

        Prioridad prioridad = prioridadRepository.findById(prioridadId)
                .orElseThrow(() -> new RuntimeException("Prioridad no existe"));

        solicitud.setPrioridad(prioridad);

        Solicitud actualizada = solicitudRepository.save(solicitud);

        historialService.registrarDesdeSistema(
                actualizada,
                "PRIORIDAD",
                "Prioridad asignada: " + prioridad.getNivelPrioridad()
        );

        return actualizada;
    }**/

    // 🔹 Obtener solicitud
    public Solicitud obtenerPorId(String id){
        return solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
    }

    // 🔹 Listar solicitudes
    public List<Solicitud> listar(){
        return solicitudRepository.findAll();
    }

    // 🔹 Cerrar solicitud
    public Solicitud cerrarSolicitud(String id){

        Solicitud solicitud = obtenerPorId(id);

        if(solicitud.getEstado() == EstadoSolicitud.CERRADA){
            throw new RuntimeException("La solicitud ya está cerrada");
        }

        solicitud.setEstado(EstadoSolicitud.CERRADA);

        Solicitud actualizada = solicitudRepository.save(solicitud);

        historialService.registrarDesdeSistema(
                actualizada,
                "CIERRE",
                "Solicitud cerrada"
        );

        return actualizada;
    }
}
