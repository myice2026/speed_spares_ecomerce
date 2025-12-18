package com.speedspares.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String referencia;
    private Double precio;
    private Integer stock;
    
    // Pilar de Confianza: Trazabilidad [cite: 7]
    private String procedenciaCertificada; 

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Usuario vendedor;

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Producto() {}

    // Getters y Setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public String getProcedenciaCertificada() { return procedenciaCertificada; }
    public void setProcedenciaCertificada(String procedenciaCertificada) { this.procedenciaCertificada = procedenciaCertificada; }

    public Integer getStock() { return stock; }
    public String getReferencia() { return referencia; }
    public Usuario getVendedor() { return vendedor; }
    public void setVendedor(Usuario vendedor) { this.vendedor = vendedor; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public void setId(Long id) {
        this.id = id;
    }
}     

