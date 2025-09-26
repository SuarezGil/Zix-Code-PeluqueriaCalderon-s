package org.zix.PeluqueriaCalderons.dominio.dto;

import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record CitaDto (
        LocalDateTime dateTime,
        Long clienteId,
        Long servicioId
) {

}