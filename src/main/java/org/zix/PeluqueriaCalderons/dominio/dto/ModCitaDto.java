
package org.zix.PeluqueriaCalderons.dominio.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ModCitaDto (
        @NotNull(message = "La fecha y hora es obligatoria")
        LocalDateTime dateTime,

        @NotNull(message = "El cliente es obligatorio")
        Long clienteId,  // FK al cliente

        @NotNull(message = "El servicio es obligatorio")
        Long servicioId  // FK al servicio
) { }
