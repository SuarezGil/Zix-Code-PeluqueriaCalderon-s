package org.zix.PeluqueriaCalderons.dominio.dto;

import java.time.LocalDate;

public record FacturaDto (
        Long codigo,
        LocalDate fecha,
        Double total,
        Long cliente_id,
        Long servicio_id
){

}
