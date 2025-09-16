package org.zix.PeluqueriaCalderons;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class CitaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(name = "fecha_hora")
    private LocalDate fechaHora;
    private Long cliente_id;
    private Long servicio_id;
}
