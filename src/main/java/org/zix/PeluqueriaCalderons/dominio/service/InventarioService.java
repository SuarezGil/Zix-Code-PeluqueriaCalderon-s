package org.zix.PeluqueriaCalderons.dominio.service;

import org.zix.PeluqueriaCalderons.dominio.dto.InventarioDto;
import org.zix.PeluqueriaCalderons.dominio.dto.CreateInventarioDto;
import org.zix.PeluqueriaCalderons.dominio.exception.InventarioNoExisteException;
import org.zix.PeluqueriaCalderons.persistence.entity.InventarioEntity;
import org.zix.PeluqueriaCalderons.dominio.repository.InventarioRepository;
import org.zix.PeluqueriaCalderons.web.mapper.InventarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private InventarioMapper inventarioMapper;

    public List<InventarioDto> getAllProductos() {
        return inventarioRepository.findAll().stream()
                .map(this::enriquecerDto)
                .collect(Collectors.toList());
    }

    public InventarioDto getProductoById(Long id) {
        InventarioEntity producto = inventarioRepository.findById(id)
                .orElseThrow(() -> new InventarioNoExisteException(id));
        return enriquecerDto(producto);
    }

    public InventarioDto getProductoByNombre(String nombreProducto) {
        InventarioEntity producto = inventarioRepository.findByNombreProducto(nombreProducto)
                .orElseThrow(() -> new InventarioNoExisteException(nombreProducto, "nombre"));
        return enriquecerDto(producto);
    }

    public List<InventarioDto> getProductosByCategoria(String categoria) {
        List<InventarioEntity> productos = inventarioRepository.findByCategoria(categoria);
        if (productos.isEmpty()) {
            throw new InventarioNoExisteException("No se encontraron productos en la categoría: " + categoria);
        }
        return productos.stream()
                .map(this::enriquecerDto)
                .collect(Collectors.toList());
    }

    public List<InventarioDto> getProductosBajoStockMinimo() {
        List<InventarioEntity> productos = inventarioRepository.findProductosBajoStockMinimo();
        return productos.stream()
                .map(this::enriquecerDto)
                .collect(Collectors.toList());
    }

    public List<InventarioDto> buscarProductosPorNombre(String nombre) {
        List<InventarioEntity> productos = inventarioRepository.findByNombreProductoContaining(nombre);
        return productos.stream()
                .map(this::enriquecerDto)
                .collect(Collectors.toList());
    }

    public InventarioDto createProducto(CreateInventarioDto inventarioDto) {
        if (inventarioRepository.existsByNombreProducto(inventarioDto.getNombreProducto())) {
            throw new RuntimeException("Ya existe un producto con el nombre: " + inventarioDto.getNombreProducto());
        }

        InventarioEntity producto = inventarioMapper.fromCreateDto(inventarioDto);
        InventarioEntity savedProducto = inventarioRepository.save(producto);
        return enriquecerDto(savedProducto);
    }

    public InventarioDto updateProducto(Long id, InventarioDto inventarioDto) {
        InventarioEntity productoExistente = inventarioRepository.findById(id)
                .orElseThrow(() -> new InventarioNoExisteException(id));

        if (inventarioRepository.existsByNombreProductoAndIdNot(inventarioDto.getNombreProducto(), id)) {
            throw new RuntimeException("Ya existe otro producto con el nombre: " + inventarioDto.getNombreProducto());
        }

        productoExistente.setNombreProducto(inventarioDto.getNombreProducto());
        productoExistente.setCantidad(inventarioDto.getCantidad());
        productoExistente.setPrecioUnitario(inventarioDto.getPrecioUnitario());
        productoExistente.setDescripcion(inventarioDto.getDescripcion());
        productoExistente.setCategoria(inventarioDto.getCategoria());
        productoExistente.setMinimoStock(inventarioDto.getMinimoStock());

        InventarioEntity updatedProducto = inventarioRepository.save(productoExistente);
        return enriquecerDto(updatedProducto);
    }

    @Transactional
    public InventarioDto agregarStock(Long id, Integer cantidad) {
        InventarioEntity producto = inventarioRepository.findById(id)
                .orElseThrow(() -> new InventarioNoExisteException(id));

        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a agregar debe ser mayor a 0");
        }

        producto.setCantidad(producto.getCantidad() + cantidad);
        InventarioEntity updatedProducto = inventarioRepository.save(producto);
        return enriquecerDto(updatedProducto);
    }

    @Transactional
    public InventarioDto reducirStock(Long id, Integer cantidad) {
        InventarioEntity producto = inventarioRepository.findById(id)
                .orElseThrow(() -> new InventarioNoExisteException(id));

        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a reducir debe ser mayor a 0");
        }

        if (producto.getCantidad() < cantidad) {
            throw new IllegalArgumentException("Stock insuficiente. Stock actual: " + producto.getCantidad());
        }

        producto.setCantidad(producto.getCantidad() - cantidad);
        InventarioEntity updatedProducto = inventarioRepository.save(producto);
        return enriquecerDto(updatedProducto);
    }

    public void deleteProducto(Long id) {
        if (!inventarioRepository.existsById(id)) {
            throw new InventarioNoExisteException(id);
        }
        inventarioRepository.deleteById(id);
    }

    public BigDecimal getValorTotalInventario() {
        return inventarioRepository.getValorTotalInventario();
    }

    public Long getTotalProductosAgotados() {
        return inventarioRepository.countProductosAgotados();
    }

    // Método auxiliar para enriquecer el DTO con información calculada
    private InventarioDto enriquecerDto(InventarioEntity entity) {
        InventarioDto dto = inventarioMapper.toDto(entity);
        dto.setValorTotal(entity.getValorTotal());
        dto.setBajoStockMinimo(entity.isBajoStockMinimo());
        return dto;
    }
}

