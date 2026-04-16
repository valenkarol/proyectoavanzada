package co.edu.uniquindio.poo.proyectoavanzada.Domain.State;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Solicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.EstadoSolicitud;

public class RegistradaState implements SolicitudState {

    @Override
    public void clasificar(Solicitud solicitud) {
        solicitud.setEstado(EstadoSolicitud.CLASIFICADA);
    }

    @Override
    public void iniciarAtencion(Solicitud solicitud) {
        throw new RuntimeException("Debe clasificar primero");
    }

    @Override
    public void finalizar(Solicitud solicitud) {
        throw new RuntimeException("Estado inválido");
    }

    @Override
    public void cerrar(Solicitud solicitud) {
        throw new RuntimeException("Estado inválido");
    }
}