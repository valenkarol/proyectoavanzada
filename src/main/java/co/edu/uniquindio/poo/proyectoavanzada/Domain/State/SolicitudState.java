package co.edu.uniquindio.poo.proyectoavanzada.Domain.State;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Solicitud;

public interface SolicitudState {

    void clasificar(Solicitud solicitud);

    void iniciarAtencion(Solicitud solicitud);

    void finalizar(Solicitud solicitud);

    void cerrar(Solicitud solicitud);

}
