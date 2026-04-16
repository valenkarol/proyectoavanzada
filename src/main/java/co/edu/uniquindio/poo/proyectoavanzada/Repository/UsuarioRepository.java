package co.edu.uniquindio.poo.proyectoavanzada.Repository;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByCorreo(String correo);
}
