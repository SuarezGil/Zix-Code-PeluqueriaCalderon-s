package org.zix.PeluqueriaCalderons.dominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zix.PeluqueriaCalderons.dominio.dto.ClienteDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModClienteDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModServicioDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ServicioDto;
import org.zix.PeluqueriaCalderons.persistence.entity.ServicioEntity;

import java.util.List;


public interface ServicioRepository  {
    List<ServicioDto> obtenerTodo();
    ServicioDto obtenerServicioPorCodigo(Long codigo);
    ServicioDto guardarServicio(ServicioDto servicioDto);
    ServicioDto modificarServicio(Long codigo, ModServicioDto servicioDto);
    void eliminarServicio(Long codigo);
}

