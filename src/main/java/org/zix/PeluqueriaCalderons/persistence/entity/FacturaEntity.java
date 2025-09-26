package org.zix.PeluqueriaCalderons.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "facturas")
@Data
public class FacturaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private LocalDate fecha;

    private Double total;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private EmpleadoEntity empleado;
}
