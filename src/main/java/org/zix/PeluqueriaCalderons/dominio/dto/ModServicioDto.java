package org.zix.PeluqueriaCalderons.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ModServicioDto (
        @NotBlank(message = "El nombre del servicio es obligatorio")
        String name,

        @NotNull(message = "El precio es obligatorio")
        @Positive(message = "El precio debe ser mayor que 0")
         Double price,

         @NotNull(message = "La duración es obligatoria")
         @Positive(message = "La duración debe ser mayor que 0")
         Integer duration



){
}
