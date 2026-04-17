package co.edu.uniquindio.poo.proyectoavanzada.DTO;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.EstadoSolicitud;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistorialResponse {

    private String accion;
    private EstadoSolicitud estadoAnterior;
    private EstadoSolicitud estadoNuevo;
    private String observacion;
    private LocalDateTime fechaHora;
}
