package org.zix.PeluqueriaCalderons.dominio.dto;

import java.time.LocalDate;

public record ClienteDto (
    String name,
    String tel,
    String email,
    LocalDate registrationDate

)
{
}
