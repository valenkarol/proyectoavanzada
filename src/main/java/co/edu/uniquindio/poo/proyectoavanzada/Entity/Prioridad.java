package co.edu.uniquindio.poo.proyectoavanzada.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "prioridades")
@Getter
@Setter
public class Prioridad {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nivelPrioridad;

    private LocalDateTime fechaLimite;

    private String justificacion;
}