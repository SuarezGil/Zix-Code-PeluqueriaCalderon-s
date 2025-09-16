package org.zix.PeluqueriaCalderons;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ClienteDto {
    Long codigo,
    @NotBlank(message = "El nombre del cliente es obligatorio")
    String name,
    @NotBlank(message = "El telefono del cliente es obligatorio")
    String phone,
    @NotBlank(message = "El correo del cliente es obligatorio")
    String correo,
    @PastOrPresent(message = "La fecha de registro no puede ser futura")
    LocalDate releaseDate
}
