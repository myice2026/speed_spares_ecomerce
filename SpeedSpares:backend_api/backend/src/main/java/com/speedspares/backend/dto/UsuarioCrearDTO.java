package com.speedspares.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCrearDTO {
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private String telefono;
    private Double latitud;
    private Double longitud;
}
