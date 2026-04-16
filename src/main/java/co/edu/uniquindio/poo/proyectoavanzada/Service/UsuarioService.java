package co.edu.uniquindio.poo.proyectoavanzada.Service;

import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Usuario;
import co.edu.uniquindio.poo.proyectoavanzada.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // 🔹 Crear usuario con validaciones
    public Usuario crearUsuario(Usuario usuario){

        // validar correo único
        usuarioRepository.findByCorreo(usuario.getCorreo())
                .ifPresent(u -> {
                    throw new RuntimeException("Ya existe un usuario con ese correo");
                });

        // validación básica
        if(usuario.getNombre() == null || usuario.getNombre().isBlank()){
            throw new RuntimeException("El nombre es obligatorio");
        }

        return usuarioRepository.save(usuario);
    }

    // 🔹 Obtener usuario
    public Usuario obtenerPorId(String id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // 🔹 Listar usuarios
    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    // 🔹 Desactivar usuario (lógica real)
    public void desactivarUsuario(String id){
        Usuario usuario = obtenerPorId(id);

        if(!usuario.isActivo()){
            throw new RuntimeException("El usuario ya está desactivado");
        }

        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }

    // 🔹 Reactivar usuario
    public void activarUsuario(String id){
        Usuario usuario = obtenerPorId(id);

        if(usuario.isActivo()){
            throw new RuntimeException("El usuario ya está activo");
        }

        usuario.setActivo(true);
        usuarioRepository.save(usuario);
    }

    // 🔹 Actualizar datos básicos
    public Usuario actualizarUsuario(String id, Usuario datos){
        Usuario usuario = obtenerPorId(id);

        usuario.setNombre(datos.getNombre());
        usuario.setCorreo(datos.getCorreo());
        usuario.setRol(datos.getRol());

        return usuarioRepository.save(usuario);
    }
}