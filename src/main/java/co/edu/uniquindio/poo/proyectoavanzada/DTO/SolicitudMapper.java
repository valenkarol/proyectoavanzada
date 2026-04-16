package co.edu.uniquindio.poo.proyectoavanzada.DTO;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Solicitud;

public class SolicitudMapper {

    public static SolicitudResponse toResponse(Solicitud solicitud){
        SolicitudResponse dto = new SolicitudResponse();

        dto.setId(solicitud.getId());
        dto.setDescripcion(solicitud.getDescripcion());
        dto.setEstado(solicitud.getEstado());
        dto.setFechaRegistro(solicitud.getFechaRegistro());
        dto.setTipoSolicitud(solicitud.getTipoSolicitud());

        return dto;
    }
}
