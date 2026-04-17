package co.edu.uniquindio.poo.proyectoavanzada.DTO;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.CanalOrigen;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.TipoSolicitud;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolicitudCreateRequest {

    @NotNull
    private TipoSolicitud tipoSolicitud;

    @NotBlank
    private String descripcion;

    @NotNull
    private CanalOrigen canalOrigen;

    @NotBlank
    private String solicitanteId;
}
