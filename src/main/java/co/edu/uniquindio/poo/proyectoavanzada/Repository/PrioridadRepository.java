package co.edu.uniquindio.poo.proyectoavanzada.Repository;

import co.edu.uniquindio.poo.proyectoavanzada.Entity.Prioridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrioridadRepository extends JpaRepository<Prioridad, String> {
}
