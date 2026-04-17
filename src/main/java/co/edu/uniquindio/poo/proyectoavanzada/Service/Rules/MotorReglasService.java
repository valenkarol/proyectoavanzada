package co.edu.uniquindio.poo.proyectoavanzada.Service.Rules;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Solicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.Prioridad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MotorReglasService {

    private final List<Regla> reglas;

    public void aplicarReglas(Solicitud solicitud) {

        for (Regla regla : reglas) {
            if (regla.cumple(solicitud)) {
                regla.aplicar(solicitud);
            }
        }

        // valor por defecto
        if (solicitud.getPrioridad() == null) {
            solicitud.setPrioridad(Prioridad.BAJA);
        }
    }
}
