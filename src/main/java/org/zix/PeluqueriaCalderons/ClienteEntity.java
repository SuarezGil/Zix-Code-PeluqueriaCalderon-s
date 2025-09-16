package org.zix.PeluqueriaCalderons;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(length = 150, unique = true, nullable = false)
    private String nombre;
    @Column(length = 20, nullable = false)
    private String telefono;
    @Column(length = 40, nullable = false)
    private String email;
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;
}
