package com.speedspares.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Long idProducto;
    private Integer idCategoria;
    private String sku;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String procedencia;
    private Long idUsuario;
}
