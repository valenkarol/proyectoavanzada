package co.edu.uniquindio.poo.proyectoavanzada.Domain.State;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Solicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.EstadoSolicitud;

public class EnAtencionState implements SolicitudState {

    @Override
    public void clasificar(Solicitud solicitud) {
        throw new RuntimeException("Estado inválido");
    }

    @Override
    public void iniciarAtencion(Solicitud solicitud) {
        throw new RuntimeException("Ya en atención");
    }

    @Override
    public void finalizar(Solicitud solicitud) {
        solicitud.setEstado(EstadoSolicitud.ATENDIDA);
    }

    @Override
    public void cerrar(Solicitud solicitud) {
        throw new RuntimeException("Debe finalizar primero");
    }
}
