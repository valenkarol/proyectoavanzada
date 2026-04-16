package co.edu.uniquindio.poo.proyectoavanzada.Domain.State;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.EstadoSolicitud;

public class SolicitudStateFactory {

    public static SolicitudState getState(EstadoSolicitud estado) {

        return switch (estado) {
            case REGISTRADA -> new RegistradaState();
            case CLASIFICADA -> new ClasificadaState();
            case EN_ATENCION -> new EnAtencionState();
            case ATENDIDA -> new AtendidaState();
            case CERRADA -> new CerradaState();
        };
    }
}
