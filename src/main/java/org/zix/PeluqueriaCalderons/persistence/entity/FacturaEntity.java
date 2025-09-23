package org.zix.PeluqueriaCalderons.persistence.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class FacturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private Double total;
    private LocalDate fecha;
    private Long clienteId;
    private Long empleadoId;
}

