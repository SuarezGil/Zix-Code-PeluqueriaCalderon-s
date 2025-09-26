package org.zix.PeluqueriaCalderons.dominio.dto;

import java.time.LocalDate;

public record ModFacturaDto (
        LocalDate fecha,
        Double total,
        Long cliente_id,
        Long empleado_id) {
}
