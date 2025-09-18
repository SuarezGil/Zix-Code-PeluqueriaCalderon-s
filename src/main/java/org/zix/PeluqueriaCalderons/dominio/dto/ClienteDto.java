package org.zix.PeluqueriaCalderons.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ClienteDto (
        @NotBlank(message = "El nombre del cliente es obligatorio")
        String name,
        @NotBlank(message = "El telefono del cliente es obligatorio")
        String tel,

        @NotBlank(message = "El correo del cliente es obligatorio")
        String email,

        @PastOrPresent(message = "La fecha de registro debe ser anterior a la fecha actual")
        LocalDate registrationDate

)
{
}
