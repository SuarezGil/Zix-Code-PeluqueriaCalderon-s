package org.zix.PeluqueriaCalderons.dominio.dto;

import java.time.LocalDateTime;

public record ModCitaDto (
        LocalDateTime fechaHora,
        Long cliente_id,
        Long servicio_id
) {
}
