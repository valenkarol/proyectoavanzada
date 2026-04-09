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

    private String justificacion; //esto
} //quitar esta clase y poner solo un string con justificacion en solicitud
// crear un enum para nivel de prioridad
// fecha limite ponerla en solicitud- es una fecha liimite para responder a la solicitud