package org.zix.PeluqueriaCalderons.dominio.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ModFacturaDto (
        @NotNull(message = "La fecha es obligatoria")
        LocalDateTime date,

        @NotNull(message = "La totalidad es obligatoria")
        @Positive(message = "El total debe ser mayor que 0")
        Double totalidad,

        @NotNull(message = "El id del cliente es obligatoria")
        Long clienteId,
        @NotNull(message = "El id del empleado es obligatoria")
        Long empleadoId
){
}
