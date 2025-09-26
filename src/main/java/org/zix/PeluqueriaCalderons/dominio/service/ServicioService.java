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

    public ServicioDto obtenerServicioPorCodigo(Long codigoServicio){
        return this.servicioRepository.obtenerServicioPorCodigo(codigoServicio);
    }

    public ServicioDto guardarServicio(ServicioDto  servicioDto){
        return this.servicioRepository.guardarServicio(servicioDto);
    }

    public ServicioDto modificarServicio(Long codigoServicio, ModServicioDto  modServicioDto){
         return this.servicioRepository.modificarServicio(codigoServicio, modServicioDto);
    }

    public void eliminarServicio(Long codigoServicio){
        this.servicioRepository.eliminarServicio(codigoServicio);
    }
}
