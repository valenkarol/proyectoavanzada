package co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity;


import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.CanalOrigen;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.EstadoSolicitud;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.Prioridad;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Enum.TipoSolicitud;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes")
@Getter
@Setter
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSolicitud estado;

    @Enumerated(EnumType.STRING)
    private Prioridad prioridad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CanalOrigen canalOrigen;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSolicitud tipoSolicitud;

    @ManyToOne(optional = false)
    @JoinColumn(name = "solicitante_id", nullable = false)
    private Usuario solicitante;

    @ManyToOne
    @JoinColumn(name = "responsable_id")
    private Usuario responsable;
}
//el ususario como responsable puede ser nullable
    //corregir el jpa (column

