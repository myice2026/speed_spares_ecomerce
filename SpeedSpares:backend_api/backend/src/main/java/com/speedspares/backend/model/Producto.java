package com.speedspares.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Productos") // Mapea a la tabla 'Productos' de tu SQL
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    // Campos Estándar de la Tabla SQL
    @Column(name = "id_categoria")
    private Integer idCategoria;
    private String sku;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock; 
    
    // LÓGICA DE SPEED SPARES (Trazabilidad)
    @Column(name = "procedencia") 
    private String procedencia; // Origen/Trazabilidad del repuesto

    // Relación: Un Producto es vendido por un Usuario (Proveedor)
    // Asumimos que la tabla Productos en tu SQL tiene una FK a Usuario
    @ManyToOne 
    @JoinColumn(name = "id_usuario") // Necesitas añadir una columna FK 'id_usuario' a la tabla Productos en MySQL
    private Usuario usuario; 

    // --- Constructor y Getters/Setters ---
    // ... (Genera el resto de los 15+ getters/setters)
}
