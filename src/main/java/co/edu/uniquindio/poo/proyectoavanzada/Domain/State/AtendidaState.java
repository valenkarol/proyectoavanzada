package co.edu.uniquindio.poo.proyectoavanzada.Domain.State;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Solicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.EstadoSolicitud;

public class AtendidaState implements SolicitudState {

    @Override
    public void clasificar(Solicitud solicitud) {
        throw new RuntimeException("Estado inválido");
    }

    @Override
    public void iniciarAtencion(Solicitud solicitud) {
        throw new RuntimeException("Estado inválido");
    }

    @Override
    public void finalizar(Solicitud solicitud) {
        throw new RuntimeException("Ya finalizada");
    }

    @Override
    public void cerrar(Solicitud solicitud) {
        solicitud.setEstado(EstadoSolicitud.CERRADA);
    }
}
