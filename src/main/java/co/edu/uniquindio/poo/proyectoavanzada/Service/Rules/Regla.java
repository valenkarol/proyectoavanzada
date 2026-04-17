package co.edu.uniquindio.poo.proyectoavanzada.Service.Rules;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Solicitud;

public interface Regla {

    boolean cumple(Solicitud solicitud);

    void aplicar(Solicitud solicitud);
}
