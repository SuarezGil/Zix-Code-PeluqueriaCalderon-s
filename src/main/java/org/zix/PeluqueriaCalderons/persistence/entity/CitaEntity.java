package org.zix.PeluqueriaCalderons.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Citas")
@Data
public class CitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;


    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "codigo_cliente", nullable = false)
    private ClienteEntity cliente; // relación con ClienteEntity

    @ManyToOne
    @JoinColumn(name = "codigo_servicio", nullable = false)
    private ServicioEntity servicio; // relación con ServicioEntity

}
