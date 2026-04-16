package co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.EstadoSolicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.TipoAccion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial")
@Getter
@Setter
public class HistorialSolicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "solicitud_id", nullable = false)
    private Solicitud solicitud;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAccion accion;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estadoAnterior;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estadoNuevo;

    @Column(length = 500)
    private String observacion;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioResponsable;
}
