package co.edu.uniquindio.poo.proyectoavanzada.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CerrarSolicitudRequest {

    @NotBlank
    private String observacion;
}
