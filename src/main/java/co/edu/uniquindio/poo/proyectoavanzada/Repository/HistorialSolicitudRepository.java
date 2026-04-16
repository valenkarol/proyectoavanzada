package co.edu.uniquindio.poo.proyectoavanzada.Repository;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.HistorialSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialSolicitudRepository extends JpaRepository<HistorialSolicitud, String> {
    List<HistorialSolicitud> findBySolicitudId(String solicitudId);
}
