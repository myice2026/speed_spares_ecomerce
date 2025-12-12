package com.speedspares.backend.service;

import com.speedspares.backend.model.Producto;
import com.speedspares.backend.model.Usuario;
import com.speedspares.backend.repository.ProductoRepository;
import com.speedspares.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto crear(Producto producto) {
        if (producto.getUsuario() != null && producto.getUsuario().getIdUsuario() != null) {
            Optional<Usuario> usuario = usuarioRepository.findById(producto.getUsuario().getIdUsuario());
            if (usuario.isPresent()) {
                producto.setUsuario(usuario.get());
            }
        }
        return productoRepository.save(producto);
    }

    public Producto actualizar(Long id, Producto productoActualizado) {
        Optional<Producto> productoExistente = productoRepository.findById(id);
        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();
            producto.setIdCategoria(productoActualizado.getIdCategoria());
            producto.setSku(productoActualizado.getSku());
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setStock(productoActualizado.getStock());
            producto.setProcedencia(productoActualizado.getProcedencia());
            return productoRepository.save(producto);
        }
        return null;
    }

    public boolean eliminar(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Producto> obtenerPorUsuario(Long idUsuario) {
        return productoRepository.findAll().stream()
                .filter(p -> p.getUsuario() != null && p.getUsuario().getIdUsuario().equals(idUsuario))
                .toList();
    }
}
