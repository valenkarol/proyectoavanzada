package co.edu.uniquindio.poo.proyectoavanzada.DTO;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.CanalOrigen;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.EstadoSolicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.Prioridad;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.TipoSolicitud;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SolicitudResponse {

    private String id;
    private TipoSolicitud tipoSolicitud;
    private String descripcion;
    private EstadoSolicitud estado;
    private CanalOrigen canalOrigen;
    private LocalDateTime fechaRegistro;

    private Prioridad prioridad; // enum
    private String responsableId; // simplificado
}
