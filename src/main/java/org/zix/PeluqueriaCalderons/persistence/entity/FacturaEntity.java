package org.zix.PeluqueriaCalderons.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="Facturas")
@Data
public class FacturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoFactura;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(length = 100,  nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "codigo_cliente")
    private ClienteEntity cliente; // relación con ClienteEntity

    @ManyToOne
    @JoinColumn(name = "codigo_empleado")
    private EmpleadoEntity empleado; //
}
