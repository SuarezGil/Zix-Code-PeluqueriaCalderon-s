package org.zix.PeluqueriaCalderons.dominio.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class CreateInventarioDto {

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(message = "El nombre del producto debe tener entre 2 y 100 caracteres")
    private String nombreProducto;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio unitario debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio unitario debe tener máximo 2 decimales")
    private BigDecimal precioUnitario;

    @Size(message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;

    @Size(message = "La categoría no puede exceder 100 caracteres")
    private String categoria;

    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    private Integer minimoStock;


    public CreateInventarioDto() {}

    public CreateInventarioDto(String nombreProducto, Integer cantidad, BigDecimal precioUnitario,
                               String descripcion, String categoria, Integer minimoStock) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.minimoStock = minimoStock;
    }


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
}
