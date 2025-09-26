package org.zix.PeluqueriaCalderons.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ModServicioDto (
        @NotBlank(message = "The name of the service is required")
        String name,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be greater than 0")
        Double price,

        @NotNull(message = "Duration is required")
        @Positive(message = "Duration must be greater than 0")
        Integer duration
) {}
