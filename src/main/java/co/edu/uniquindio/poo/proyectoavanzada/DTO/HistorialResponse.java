package co.edu.uniquindio.poo.proyectoavanzada.DTO;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.EstadoSolicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.TipoAccion;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistorialResponse {

    private TipoAccion accion;
    private String id;
    private String observacion;
    private EstadoSolicitud estadoAnterior;
    private EstadoSolicitud estadoNuevo;
    private LocalDateTime fechaHora;
}
