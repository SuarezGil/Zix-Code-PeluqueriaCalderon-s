package org.zix.PeluqueriaCalderons.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name ="Servicios")
@Data
public class ServicioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_servicio")
    private Long codigoServicio;

    @Column(length = 100,  nullable = false, unique = true)
    private String nombre;

    @Column(length = 100,  nullable = false)
    private Double precio;

    @Column(length = 100,  nullable = false)
    private Integer duracion;

}
