package co.edu.uniquindio.poo.proyectoavanzada.Domain.State;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Solicitud;

public class CerradaState implements SolicitudState {

    @Override
    public void clasificar(Solicitud solicitud) {
        throw new RuntimeException("Solicitud cerrada");
    }

    @Override
    public void iniciarAtencion(Solicitud solicitud) {
        throw new RuntimeException("Solicitud cerrada");
    }

    @Override
    public void finalizar(Solicitud solicitud) {
        throw new RuntimeException("Solicitud cerrada");
    }

    @Override
    public void cerrar(Solicitud solicitud) {
        throw new RuntimeException("Ya está cerrada");
    }
}
