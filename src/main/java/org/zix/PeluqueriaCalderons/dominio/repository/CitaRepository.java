package org.zix.PeluqueriaCalderons.dominio.repository;

import org.zix.PeluqueriaCalderons.dominio.dto.CitaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModCitaDto;

import java.util.List;

public interface CitaRepository {
    List<CitaDto> obtenerTodo();
    CitaDto buscarPorId(Long codigo);
    CitaDto guardarCita(CitaDto citaDto);
    void eliminarCita(Long codigo);

    CitaDto modificarCita(Long codigo, ModCitaDto citaDto);
}
