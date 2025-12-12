package com.speedspares.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long idUsuario;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private LocalDateTime fechaRegistro;
    private boolean activo;
    private boolean esProveedorVerificado;
    private Double latitud;
    private Double longitud;
}
