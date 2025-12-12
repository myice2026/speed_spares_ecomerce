package com.speedspares.backend.controller;

import com.speedspares.backend.dto.ProductoCrearDTO;
import com.speedspares.backend.dto.ProductoDTO;
import com.speedspares.backend.model.Producto;
import com.speedspares.backend.model.Usuario;
import com.speedspares.backend.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerTodos() {
        List<Producto> productos = productoService.obtenerTodos();
        List<ProductoDTO> dtos = productos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.obtenerPorId(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(convertToDTO(producto.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@RequestBody ProductoCrearDTO productoDTO) {
        Producto producto = new Producto();
        producto.setIdCategoria(productoDTO.getIdCategoria());
        producto.setSku(productoDTO.getSku());
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setProcedencia(productoDTO.getProcedencia());

        if (productoDTO.getIdUsuario() != null) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(productoDTO.getIdUsuario());
            producto.setUsuario(usuario);
        }

        Producto nuevoProducto = productoService.crear(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(nuevoProducto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable Long id, @RequestBody ProductoCrearDTO productoDTO) {
        Producto producto = new Producto();
        producto.setIdCategoria(productoDTO.getIdCategoria());
        producto.setSku(productoDTO.getSku());
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setProcedencia(productoDTO.getProcedencia());

        Producto productoActualizado = productoService.actualizar(id, producto);
        if (productoActualizado != null) {
            return ResponseEntity.ok(convertToDTO(productoActualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = productoService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ProductoDTO>> obtenerPorUsuario(@PathVariable Long idUsuario) {
        List<Producto> productos = productoService.obtenerPorUsuario(idUsuario);
        List<ProductoDTO> dtos = productos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    private ProductoDTO convertToDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(producto.getIdProducto());
        dto.setIdCategoria(producto.getIdCategoria());
        dto.setSku(producto.getSku());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setProcedencia(producto.getProcedencia());
        if (producto.getUsuario() != null) {
            dto.setIdUsuario(producto.getUsuario().getIdUsuario());
        }
        return dto;
    }
}
