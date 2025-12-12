package com.speedspares.backend.model;

import jakarta.persistence.*;
import java.sql.Timestamp; // Para la fecha de registro

@Entity
@Table(name = "Usuario") // Mapea a la tabla 'Usuario' de tu SQL
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario") 
    private Long idUsuario;
    
    // Campos Estándar de la Tabla SQL
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private String telefono;
    private Timestamp fechaRegistro; // Tipo Timestamp para la fecha
    private boolean activo;
    
    // CAMPOS DE LA LÓGICA DE SPEED SPARES (Debes asegurar que existan en tu DB)
    
    // Campo clave para la Confianza. Asumimos que esta columna existe en MySQL.
    @Column(name = "es_proveedor_verificado") 
    private boolean esProveedorVerificado; 
    
    // Campos clave para la Hiperlocalización
    private Double latitud; 
    private Double longitud; 

    // --- Constructor y Getters/Setters ---
    // (Genera el resto de los Getters/Setters para todos los campos)
    public Long getIdUsuario() { return idUsuario; }
    //... (el resto de los 12 getters/setters)
}
