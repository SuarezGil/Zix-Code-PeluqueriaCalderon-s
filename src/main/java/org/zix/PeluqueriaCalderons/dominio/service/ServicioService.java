package org.zix.PeluqueriaCalderons.dominio.service;

import org.springframework.stereotype.Service;
import org.zix.PeluqueriaCalderons.dominio.dto.ModServicioDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ServicioDto;
import org.zix.PeluqueriaCalderons.dominio.repository.ServicioRepository;

import java.util.List;

@Service
public class ServicioService {
    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public List<ServicioDto> obtenerTodo(){
        return this.servicioRepository.obtenerTodo();
    }

    public ServicioDto obtenerServicioPorCodigo(Long codigo){
        return this.servicioRepository.obtenerServicioPorCodigo(codigo);
    }

    public ServicioDto guardarServicio(ServicioDto  servicioDto){
        return this.servicioRepository.guardarServicio(servicioDto);
    }

    public ServicioDto modificarServicio(Long codigo, ModServicioDto  modServicioDto){
         return this.servicioRepository.modificarServicio(codigo, modServicioDto);
    }

    public void eliminarServicio(Long codigo){
        this.servicioRepository.eliminarServicio(codigo);
    }
}
