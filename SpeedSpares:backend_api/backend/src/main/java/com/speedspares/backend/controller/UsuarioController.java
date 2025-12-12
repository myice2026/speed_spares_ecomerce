package com.speedspares.backend.controller;

import com.speedspares.backend.dto.UsuarioCrearDTO;
import com.speedspares.backend.dto.UsuarioDTO;
import com.speedspares.backend.model.Usuario;
import com.speedspares.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obtenerTodos() {
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        List<UsuarioDTO> dtos = usuarios.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obtenerPorId(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(convertToDTO(usuario.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> crear(@RequestBody UsuarioCrearDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setLatitud(usuarioDTO.getLatitud());
        usuario.setLongitud(usuarioDTO.getLongitud());

        Usuario nuevoUsuario = usuarioService.crear(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(nuevoUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(@PathVariable Long id, @RequestBody UsuarioCrearDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setLatitud(usuarioDTO.getLatitud());
        usuario.setLongitud(usuarioDTO.getLongitud());

        Usuario usuarioActualizado = usuarioService.actualizar(id, usuario);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok(convertToDTO(usuarioActualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = usuarioService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/verificar")
    public ResponseEntity<UsuarioDTO> verificarProveedor(@PathVariable Long id) {
        Usuario usuario = usuarioService.verificarProveedor(id);
        if (usuario != null) {
            return ResponseEntity.ok(convertToDTO(usuario));
        }
        return ResponseEntity.notFound().build();
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setApellidos(usuario.getApellidos());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        dto.setFechaRegistro(usuario.getFechaRegistro());
        dto.setActivo(usuario.isActivo());
        dto.setEsProveedorVerificado(usuario.isEsProveedorVerificado());
        dto.setLatitud(usuario.getLatitud());
        dto.setLongitud(usuario.getLongitud());
        return dto;
    }
}
