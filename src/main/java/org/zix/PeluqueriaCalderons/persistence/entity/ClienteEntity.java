package org.zix.PeluqueriaCalderons.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Clientes")
@Data
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(length = 150, unique = true, nullable = false)
    private String nombre;
    @Column(precision = 3, nullable = false)
    private String telefono;
    @Column(length = 40, nullable = false)
    private String correo;
    @Column(name = "fecha_registro")
    private LocalDate fechaRegsitro;
}

