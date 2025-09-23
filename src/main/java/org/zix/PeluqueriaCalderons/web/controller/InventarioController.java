package org.zix.PeluqueriaCalderons.web.controller;

import org.zix.PeluqueriaCalderons.dominio.dto.InventarioDto;
import org.zix.PeluqueriaCalderons.dominio.dto.CreateInventarioDto;
import org.zix.PeluqueriaCalderons.dominio.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<InventarioDto>> getAllProductos() {
        List<InventarioDto> productos = inventarioService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioDto> getProductoById(@PathVariable Long id) {
        InventarioDto producto = inventarioService.getProductoById(id);
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/nombre/{nombreProducto}")
    public ResponseEntity<InventarioDto> getProductoByNombre(@PathVariable String nombreProducto) {
        InventarioDto producto = inventarioService.getProductoByNombre(nombreProducto);
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<InventarioDto>> getProductosByCategoria(@PathVariable String categoria) {
        List<InventarioDto> productos = inventarioService.getProductosByCategoria(categoria);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/bajo-stock")
    public ResponseEntity<List<InventarioDto>> getProductosBajoStockMinimo() {
        List<InventarioDto> productos = inventarioService.getProductosBajoStockMinimo();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<InventarioDto>> buscarProductosPorNombre(@RequestParam String nombre) {
        List<InventarioDto> productos = inventarioService.buscarProductosPorNombre(nombre);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/valor-total")
    public ResponseEntity<BigDecimal> getValorTotalInventario() {
        BigDecimal valorTotal = inventarioService.getValorTotalInventario();
        return ResponseEntity.ok(valorTotal);
    }

    @GetMapping("/agotados/count")
    public ResponseEntity<Long> getTotalProductosAgotados() {
        Long count = inventarioService.getTotalProductosAgotados();
        return ResponseEntity.ok(count);
    }

    @PostMapping
    public ResponseEntity<?> createProducto(@Validated @RequestBody CreateInventarioDto inventarioDto) {
        InventarioDto nuevoProducto = inventarioService.createProducto(inventarioDto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Long id, @Validated @RequestBody InventarioDto inventarioDto) {
        InventarioDto productoActualizado = inventarioService.updateProducto(id, inventarioDto);
        return ResponseEntity.ok(productoActualizado);
    }

    @PatchMapping("/{id}/agregar-stock")
    public ResponseEntity<InventarioDto> agregarStock(@PathVariable Long id, @RequestParam Integer cantidad) {
        InventarioDto productoActualizado = inventarioService.agregarStock(id, cantidad);
        return ResponseEntity.ok(productoActualizado);
    }

    @PatchMapping("/{id}/reducir-stock")
    public ResponseEntity<InventarioDto> reducirStock(@PathVariable Long id, @RequestParam Integer cantidad) {
        InventarioDto productoActualizado = inventarioService.reducirStock(id, cantidad);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        inventarioService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
