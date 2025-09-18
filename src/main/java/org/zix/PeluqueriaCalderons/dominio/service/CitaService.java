package org.zix.PeluqueriaCalderons.dominio.service;

import org.zix.PeluqueriaCalderons.dominio.dto.CitaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModCitaDto;
import org.zix.PeluqueriaCalderons.dominio.repository.CitaRepository;

import java.util.List;

public class CitaService {
    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }


    public List<CitaDto> obtenerTodo() {
        return this.citaRepository.obtenerTodo();
    }

    public  CitaDto buscarPorId(Long codigo){
        return this.citaRepository.buscarPorId(codigo);
    }

    public CitaDto guardarCita(CitaDto citaDto){
        return this.citaRepository.guardarCita(citaDto);

    }

    public CitaDto modificarCita(Long codigo, ModCitaDto citaDto){
        return this.citaRepository.modificarCita(codigo ,citaDto);
    }

    public void eliminarCita(Long codigo){
        this.citaRepository.eliminarCita(codigo);
    }
}
