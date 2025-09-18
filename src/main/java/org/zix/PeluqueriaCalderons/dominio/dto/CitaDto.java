package org.zix.PeluqueriaCalderons.dominio.dto;

import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;

public record CitaDto (
        Long codigo,
        LocalDate releaseDate,
        Long cliente_id,
        Long servicio_id
) {

}
