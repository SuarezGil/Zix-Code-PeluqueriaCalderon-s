package org.zix.PeluqueriaCalderons;

import java.util.List;

public class CitaRepository {
    List<CitaDto> obtenerTodo();
    CitaDto buscarPorId(Long codigo);
    CitaDto guardarCita(CitaDto citaDto);
    CitaDto modificarPelicula(Long codigo, ModCitaDto citaDto);
    void eliminarCita(Long codigo);
}
