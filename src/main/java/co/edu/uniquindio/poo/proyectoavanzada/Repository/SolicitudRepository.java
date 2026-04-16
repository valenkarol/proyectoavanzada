package co.edu.uniquindio.poo.proyectoavanzada.Repository;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Solicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.EstadoSolicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.TipoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, String> {
    List<Solicitud> findByEstado(EstadoSolicitud estado);
    List<Solicitud> findByTipoSolicitud(TipoSolicitud tipoSolicitud);
    List<Solicitud> findByResponsableId(String responsableId);
    List<Solicitud> findByEstadoAndTipoSolicitud(EstadoSolicitud estado, TipoSolicitud tipoSolicitud);
}
