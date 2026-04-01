package co.edu.uniquindio.poo.proyectoavanzada.Repository;

import co.edu.uniquindio.poo.proyectoavanzada.Entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, String> {
}
