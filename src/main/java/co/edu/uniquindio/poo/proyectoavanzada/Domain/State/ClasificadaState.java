package co.edu.uniquindio.poo.proyectoavanzada.Domain.State;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Solicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.EstadoSolicitud;

public class ClasificadaState implements SolicitudState {

    @Override
    public void clasificar(Solicitud solicitud) {
        throw new RuntimeException("Ya está clasificada");
    }

    @Override
    public void iniciarAtencion(Solicitud solicitud) {
        solicitud.setEstado(EstadoSolicitud.EN_ATENCION);
    }

    @Override
    public void finalizar(Solicitud solicitud) {
        throw new RuntimeException("Debe iniciar atención primero");
    }

    @Override
    public void cerrar(Solicitud solicitud) {
        throw new RuntimeException("Estado inválido");
    }
}
