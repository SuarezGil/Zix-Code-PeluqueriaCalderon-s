package org.zix.PeluqueriaCalderons.dominio.repository;

import org.zix.PeluqueriaCalderons.dominio.dto.CitaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModCitaDto;

import java.util.List;

public interface CitaRepository {
    List<CitaDto> obtenerTodo();
    CitaDto buscarCitaPorCodigo(Long codigo);
    CitaDto guardarCita(ModCitaDto citaDto);
    CitaDto modificarCita(Long codigo, ModCitaDto citaDto);
    void eliminarCita(Long codigo);
}
