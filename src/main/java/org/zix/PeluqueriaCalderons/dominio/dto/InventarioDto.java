package org.zix.PeluqueriaCalderons.dominio.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class InventarioDto {

    private Long id;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre del producto debe tener entre 2 y 100 caracteres")
    private String nombreProducto;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio unitario debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio unitario debe tener máximo 2 decimales")
    private BigDecimal precioUnitario;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;

    @Size(max = 100, message = "La categoría no puede exceder 100 caracteres")
    private String categoria;

    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    private Integer minimoStock;

    private BigDecimal valorTotal;
    private Boolean bajoStockMinimo;


    public InventarioDto() {}

    public InventarioDto(Long id, String nombreProducto, Integer cantidad, BigDecimal precioUnitario,
                         String descripcion, String categoria, Integer minimoStock) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.minimoStock = minimoStock;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Integer getMinimoStock() { return minimoStock; }
    public void setMinimoStock(Integer minimoStock) { this.minimoStock = minimoStock; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public Boolean getBajoStockMinimo() { return bajoStockMinimo; }
    public void setBajoStockMinimo(Boolean bajoStockMinimo) { this.bajoStockMinimo = bajoStockMinimo; }
}
