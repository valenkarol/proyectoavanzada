package co.edu.uniquindio.poo.proyectoavanzada.Entity;

import co.edu.uniquindio.poo.proyectoavanzada.Enum.Rol;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nombre;
    private String correo;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    private boolean activo = true;

}
