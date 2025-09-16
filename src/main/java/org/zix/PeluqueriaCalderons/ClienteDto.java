package org.zix.PeluqueriaCalderons;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ClienteDto {
    Long codigo,
    @PastOrPresent(message = "La fecha de registro no puede ser futura")
    LocalDate releaseDate,
    Long cliente_id,
    Long servicio_id
}
