package org.zix.PeluqueriaCalderons.persistence;

import org.springframework.stereotype.Repository;
import org.zix.PeluqueriaCalderons.dominio.dto.ModServicioDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ServicioDto;
import org.zix.PeluqueriaCalderons.dominio.exception.ServicioNoExisteException;
import org.zix.PeluqueriaCalderons.dominio.exception.ServicioYaExisteException;
import org.zix.PeluqueriaCalderons.dominio.repository.ServicioRepository;
import org.zix.PeluqueriaCalderons.persistence.crud.CrudServicioEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.ClienteEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.ServicioEntity;
import org.zix.PeluqueriaCalderons.web.mapper.ServicioMapper;

import java.util.List;

@Repository
public class ServicioEntityRespository implements ServicioRepository {
    private final CrudServicioEntity crudServicio;
    private final ServicioMapper servicioMapper;

    public ServicioEntityRespository(CrudServicioEntity crudServicio, ServicioMapper servicioMapper) {
        this.crudServicio = crudServicio;
        this.servicioMapper = servicioMapper;
    }

    @Override
    public List<ServicioDto> obtenerTodo() {

        return this.servicioMapper.toDto(this.crudServicio.findAll());
    }

    @Override
    public ServicioDto obtenerServicioPorCodigo(Long codigoServicio) {
        ServicioEntity servicio = this.crudServicio.findById(codigoServicio).orElseThrow(()->new ServicioNoExisteException(codigoServicio));
        return this.servicioMapper.toDto(servicio);
    }

    @Override
    public ServicioDto guardarServicio(ServicioDto servicioDto) {
        ServicioEntity servicio = this.servicioMapper.toEntity(servicioDto);
        if (this.crudServicio.findFirstByNombre(servicioDto.name())!=null) {
            throw new ServicioYaExisteException(servicioDto.name());
        }
        this.crudServicio.save(servicio);
        return this.servicioMapper.toDto(servicio);
    }

    @Override
    public ServicioDto modificarServicio(Long codigoServicio, ModServicioDto modServicioDto) {
        ServicioEntity servicio = this.crudServicio.findById(codigoServicio).orElseThrow(()->new ServicioNoExisteException(codigoServicio));
        this.servicioMapper.modificarEntityFromDto(modServicioDto, servicio);
        return this.servicioMapper.toDto(this.crudServicio.save(servicio));
    }

    @Override
    public void eliminarServicio(Long codigoServicio) {
        ServicioEntity servicioEntity = this.crudServicio.findById(codigoServicio).orElseThrow(()->new ServicioNoExisteException(codigoServicio));
         this.crudServicio.deleteById(codigoServicio);

    }
}
