package co.edu.uniquindio.poo.proyectoavanzada.Service;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Solicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Usuario;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.EstadoSolicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.CanalOrigen;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.TipoAccion;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.State.SolicitudState;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.State.SolicitudStateFactory;
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
    public Solicitud registrarSolicitud(
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
                TipoAccion.REGISTRO,
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

        if(solicitud.getResponsable() != null){
            throw new RuntimeException("Ya tiene responsable asignado");
        }

        solicitud.setResponsable(responsable);

        Solicitud actualizada = solicitudRepository.save(solicitud);

        historialService.registrarDesdeSistema(
                actualizada,
                TipoAccion.ASIGNACION,
                "Responsable asignado"
        );

        return actualizada;
    }



    // 🔹 Obtener solicitud
    public Solicitud obtenerPorId(String id){
        return solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
    }

    // 🔹 Listar solicitudes
    public List<Solicitud> listar(){
        return solicitudRepository.findAll();
    }


    public Solicitud clasificarSolicitud(String id){

        Solicitud solicitud = obtenerPorId(id);

        EstadoSolicitud estadoAnterior = solicitud.getEstado();

        SolicitudState state = SolicitudStateFactory.getState(estadoAnterior);

        state.clasificar(solicitud);

        Solicitud actualizada = solicitudRepository.save(solicitud);

        historialService.registrarCambioEstado(
                actualizada,
                estadoAnterior,
                solicitud.getEstado(),
                TipoAccion.CLASIFICACION,
                "Solicitud clasificada"
        );

        return actualizada;
    }

    public Solicitud iniciarAtencion(String id){

        Solicitud solicitud = obtenerPorId(id);

        if(solicitud.getResponsable() == null){
            throw new RuntimeException("Debe tener responsable asignado");
        }

        EstadoSolicitud estadoAnterior = solicitud.getEstado();

        SolicitudState state = SolicitudStateFactory.getState(estadoAnterior);

        state.iniciarAtencion(solicitud);

        Solicitud actualizada = solicitudRepository.save(solicitud);

        historialService.registrarCambioEstado(
                actualizada,
                estadoAnterior,
                solicitud.getEstado(),
                TipoAccion.INICIO_ATENCION,
                "Solicitud iniciada"
        );

        return actualizada;
    }

    public Solicitud finalizarSolicitud(String id){

        Solicitud solicitud = obtenerPorId(id);

        EstadoSolicitud estadoAnterior = solicitud.getEstado();

        SolicitudState state = SolicitudStateFactory.getState(estadoAnterior);

        state.finalizar(solicitud);

        Solicitud actualizada = solicitudRepository.save(solicitud);

        historialService.registrarCambioEstado(
                actualizada,
                estadoAnterior,
                solicitud.getEstado(),
                TipoAccion.FINALIZACION,
                "Solicitud finalizada"
        );

        return actualizada;
    }

    public Solicitud cerrarSolicitud(String id, String observacion){

        Solicitud solicitud = obtenerPorId(id);

        EstadoSolicitud estadoAnterior = solicitud.getEstado();

        SolicitudState state = SolicitudStateFactory.getState(estadoAnterior);

        state.cerrar(solicitud);

        Solicitud actualizada = solicitudRepository.save(solicitud);

        historialService.registrarCambioEstado(
                actualizada,
                estadoAnterior,
                solicitud.getEstado(),
                TipoAccion.CIERRE,
                "Solicitud cerrada"
        );
        historialService.registrarDesdeSistema(
                solicitud,
                TipoAccion.CIERRE,
                observacion
        );

        return actualizada;
    }
}
