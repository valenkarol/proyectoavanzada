package co.edu.uniquindio.poo.proyectoavanzada.Mapper;

import co.edu.uniquindio.poo.proyectoavanzada.DTO.SolicitudResponse;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Solicitud;

public class SolicitudMapper {

    public static SolicitudResponse toResponse(Solicitud solicitud){
        SolicitudResponse dto = new SolicitudResponse();

        dto.setId(solicitud.getId());
        dto.setDescripcion(solicitud.getDescripcion());
        dto.setEstado(solicitud.getEstado());
        dto.setFechaRegistro(solicitud.getFechaRegistro());
        dto.setTipoSolicitud(solicitud.getTipoSolicitud());
        dto.setCanalOrigen(solicitud.getCanalOrigen());
        dto.setPrioridad(solicitud.getPrioridad());

        if(solicitud.getResponsable() != null){
            dto.setResponsableId(solicitud.getResponsable().getId());
        }

        return dto;
    }
}
