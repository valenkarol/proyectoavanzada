package co.edu.uniquindio.poo.proyectoavanzada.Service.Rules;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Solicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.CanalOrigen;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.Prioridad;
import org.springframework.stereotype.Component;

@Component
public class ReglaCanalEmail implements Regla {

    @Override
    public boolean cumple(Solicitud solicitud) {
        return solicitud.getCanalOrigen() == CanalOrigen.CORREO;
    }

    @Override
    public void aplicar(Solicitud solicitud) {
        if (solicitud.getPrioridad() == null) {
            solicitud.setPrioridad(Prioridad.MEDIA);
        }
    }
}
