package org.zix.PeluqueriaCalderons.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Clientes")
@Data
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_cliente")
    private Long codigoCliente;

    @Column(length = 150, unique = true, nullable = false)
    private String nombre;

    @Column(precision = 3, nullable = false)
    private String telefono;

    @Column(length = 40, nullable = false)
    private String correo;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;}