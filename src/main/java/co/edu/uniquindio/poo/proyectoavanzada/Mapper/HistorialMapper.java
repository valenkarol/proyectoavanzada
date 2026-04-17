package co.edu.uniquindio.poo.proyectoavanzada.Mapper;

import co.edu.uniquindio.poo.proyectoavanzada.DTO.HistorialResponse;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.HistorialSolicitud;

public class HistorialMapper {

    private HistorialMapper() {}

    public static HistorialResponse toResponse(HistorialSolicitud h){
        HistorialResponse dto = new HistorialResponse();

        dto.setAccion(h.getAccion());
        dto.setObservacion(h.getObservacion());
        dto.setEstadoAnterior(h.getEstadoAnterior());
        dto.setEstadoNuevo(h.getEstadoNuevo());
        dto.setFechaHora(h.getFechaHora());

        return dto;
    }
}
