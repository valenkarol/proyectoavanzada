package co.edu.uniquindio.poo.proyectoavanzada.Entity;


import co.edu.uniquindio.poo.proyectoavanzada.Enum.CanalOrigen;
import co.edu.uniquindio.poo.proyectoavanzada.Enum.EstadoSolicitud;
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

    private String descripcion;

    private LocalDateTime fechaRegistro;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;

    @ManyToOne
    @JoinColumn(name = "prioridad_id")
    private Prioridad prioridad;

    @Enumerated(EnumType.STRING)
    private CanalOrigen canalOrigen;

    @ManyToOne
    @JoinColumn(name = "solicitante_id")
    private Usuario solicitante;

    @ManyToOne
    @JoinColumn(name = "responsable_id")
    private Usuario responsable;
//el ususario como responsable puede ser nullable
    //corregir el jpa (column
}
