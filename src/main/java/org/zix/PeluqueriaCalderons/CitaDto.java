package org.zix.PeluqueriaCalderons;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record CitaDto {
    Long codigo,
    @FutureOrPresent(message = "La fecha de registro no puede ser anterior a la actual")
    LocalDate releaseDate,
    Long cliente_id,
    Long servicio_id
}
