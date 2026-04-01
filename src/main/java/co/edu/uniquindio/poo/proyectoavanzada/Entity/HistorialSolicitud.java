package co.edu.uniquindio.poo.proyectoavanzada.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_solicitudes")
@Getter
@Setter
public class HistorialSolicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private LocalDateTime fechaHora;

    private String accion;

    private String observacion;

    @ManyToOne
    private Usuario usuarioResponsable;

    @ManyToOne
    private Solicitud solicitud;
}
