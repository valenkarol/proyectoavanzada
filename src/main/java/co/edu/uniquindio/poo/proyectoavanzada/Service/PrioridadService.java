package co.edu.uniquindio.poo.proyectoavanzada.Service;

import co.edu.uniquindio.poo.proyectoavanzada.Entity.Prioridad;
import co.edu.uniquindio.poo.proyectoavanzada.Entity.Solicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Repository.PrioridadRepository;
import co.edu.uniquindio.poo.proyectoavanzada.Repository.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrioridadService {

    private final PrioridadRepository prioridadRepository;
    private final SolicitudRepository solicitudRepository;
    private final HistorialSolicitudService historialService;

    // 🔹 Crear prioridad
    public Prioridad crearPrioridad(
            String nivel,
            LocalDateTime fechaLimite,
            String justificacion
    ){

        if(nivel == null || nivel.isBlank()){
            throw new RuntimeException("El nivel de prioridad es obligatorio");
        }

        if(fechaLimite == null){
            throw new RuntimeException("La fecha límite es obligatoria");
        }

        Prioridad prioridad = new Prioridad();
        prioridad.setNivelPrioridad(nivel);
        prioridad.setFechaLimite(fechaLimite);
        prioridad.setJustificacion(justificacion);

        return prioridadRepository.save(prioridad);
    }

    // 🔹 Asignar prioridad a una solicitud (CLAVE en el UML)
    public Solicitud asignarPrioridadASolicitud(String solicitudId, String prioridadId){

        Solicitud solicitud = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        Prioridad prioridad = prioridadRepository.findById(prioridadId)
                .orElseThrow(() -> new RuntimeException("Prioridad no encontrada"));

        solicitud.setPrioridad(prioridad);

        Solicitud actualizada = solicitudRepository.save(solicitud);

        // 🔥 registrar en historial
        historialService.registrarDesdeSistema(
                actualizada,
                "PRIORIDAD",
                "Asignada prioridad: " + prioridad.getNivelPrioridad()
        );

        return actualizada;
    }

    // 🔹 Obtener prioridad
    public Prioridad obtenerPorId(String id){
        return prioridadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prioridad no encontrada"));
    }

    // 🔹 Listar prioridades
    public List<Prioridad> listar(){
        return prioridadRepository.findAll();
    }

    // 🔹 Actualizar prioridad
    public Prioridad actualizar(String id, Prioridad datos){

        Prioridad prioridad = obtenerPorId(id);

        prioridad.setNivelPrioridad(datos.getNivelPrioridad());
        prioridad.setFechaLimite(datos.getFechaLimite());
        prioridad.setJustificacion(datos.getJustificacion());

        return prioridadRepository.save(prioridad);
    }

    // 🔹 Eliminar prioridad (con validación)
    public void eliminar(String id){

        Prioridad prioridad = obtenerPorId(id);

        // validar que no esté en uso
        boolean enUso = solicitudRepository.findAll().stream()
                .anyMatch(s -> s.getPrioridad() != null &&
                        s.getPrioridad().getId().equals(id));

        if(enUso){
            throw new RuntimeException("No se puede eliminar, está en uso por solicitudes");
        }

        prioridadRepository.delete(prioridad);
    }
}
