package org.zix.PeluqueriaCalderons.dominio.dto;

import java.time.LocalDate;

public record ModCitaDto (
        LocalDate releaseDate,
        Long cliente_id,
        Long servicio_id
) {
}
