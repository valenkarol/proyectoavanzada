package co.edu.uniquindio.poo.proyectoavanzada.Service.Rules;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Solicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.Prioridad;
import org.springframework.stereotype.Component;

@Component
public class ReglaPrioridadUrgente implements Regla {

    @Override
    public boolean cumple(Solicitud solicitud) {
        return solicitud.getDescripcion() != null &&
                solicitud.getDescripcion().toLowerCase().contains("urgente");
    }

    @Override
    public void aplicar(Solicitud solicitud) {
        solicitud.setPrioridad(Prioridad.ALTA);
    }
}
