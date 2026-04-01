package co.edu.uniquindio.poo.proyectoavanzada.Controller;

import co.edu.uniquindio.poo.proyectoavanzada.Entity.Usuario;
import co.edu.uniquindio.poo.proyectoavanzada.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // 🔹 Crear usuario
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    // 🔹 Obtener usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable String id){
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    // 🔹 Listar usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        return ResponseEntity.ok(usuarioService.listar());
    }

    // 🔹 Desactivar usuario
    @PutMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivar(@PathVariable String id){
        usuarioService.desactivarUsuario(id);
        return ResponseEntity.ok().build();
    }

    // 🔹 Activar usuario
    @PutMapping("/{id}/activar")
    public ResponseEntity<Void> activar(@PathVariable String id){
        usuarioService.activarUsuario(id);
        return ResponseEntity.ok().build();
    }

    // 🔹 Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(
            @PathVariable String id,
            @RequestBody Usuario usuario
    ){
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuario));
    }
}
