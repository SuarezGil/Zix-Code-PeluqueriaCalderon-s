package org.zix.PeluqueriaCalderons.dominio.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FacturaDto (
        @NotNull(message = "La fecha y hora no puede estar vacía")
        LocalDateTime date,

        @NotNull(message = "El cliente es obligatorio")
        Long clienteId,

        @NotNull(message = "El empleado es obligatorio")
        Long empleadoId,

        @NotNull(message = "El total no puede estar vacío")
        @Positive(message = "El total debe ser mayor que 0")
        Double totalidad
) {}

