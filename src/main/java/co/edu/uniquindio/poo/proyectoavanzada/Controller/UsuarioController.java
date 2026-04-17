package co.edu.uniquindio.poo.proyectoavanzada.Controller;

import co.edu.uniquindio.poo.proyectoavanzada.DTO.UsuarioDTO;
import co.edu.uniquindio.poo.proyectoavanzada.Domain.Entity.Usuario;
import co.edu.uniquindio.poo.proyectoavanzada.Mapper.UsuarioMapper;
import co.edu.uniquindio.poo.proyectoavanzada.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// TAL VEZ ESTE ES EL QUE MANEJA CASI TODO - Aqui va lo de authController, para eso es el dto (enviar y recibir lo que necesitemos)
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    // 🔹 Crear usuario
    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO dto){
        Usuario usuario = usuarioMapper.toEntity(dto);
        Usuario creado = usuarioService.crearUsuario(usuario);
        return ResponseEntity.ok(usuarioMapper.toDTO(creado));
    }

    // 🔹 Obtener usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtener(@PathVariable String id){
        return ResponseEntity.ok(
                usuarioMapper.toDTO(usuarioService.obtenerPorId(id))
        );
    }

    // 🔹 Listar usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar(){
        return ResponseEntity.ok(
                usuarioService.listar()
                        .stream()
                        .map(usuarioMapper::toDTO)
                        .toList()
        );
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
    public ResponseEntity<UsuarioDTO> actualizar(
            @PathVariable String id,
            @RequestBody UsuarioDTO dto
    ){
        Usuario usuario = usuarioMapper.toEntity(dto);
        return ResponseEntity.ok(
                usuarioMapper.toDTO(
                        usuarioService.actualizarUsuario(id, usuario)
                )
        );
    }
}
