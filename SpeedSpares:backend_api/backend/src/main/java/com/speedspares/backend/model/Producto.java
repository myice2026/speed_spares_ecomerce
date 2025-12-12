package com.speedspares.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "id_categoria")
    private Integer idCategoria;
    private String sku;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    
    @Column(name = "procedencia") 
    private String procedencia;

    @ManyToOne 
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
