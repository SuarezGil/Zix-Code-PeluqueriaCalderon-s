package org.zix.PeluqueriaCalderons.persistence;

import org.springframework.stereotype.Repository;
import org.zix.PeluqueriaCalderons.dominio.dto.CitaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModCitaDto;
import org.zix.PeluqueriaCalderons.dominio.exception.CitaNoExisteException;
import org.zix.PeluqueriaCalderons.dominio.exception.CitaYaExisteException;
import org.zix.PeluqueriaCalderons.dominio.repository.CitaRepository;
import org.zix.PeluqueriaCalderons.persistence.crud.CitaCrud;
import org.zix.PeluqueriaCalderons.persistence.entity.CitaEntity;
import org.zix.PeluqueriaCalderons.web.mapper.CitaMapper;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public class CitaEntityRepository implements CitaRepository {
    private final CitaCrud citaCrud;
    private final CitaMapper citaMapper;

    public CitaEntityRepository(CitaCrud citaCrud, CitaMapper citaMapper) {
        this.citaCrud = citaCrud;
        this.citaMapper = citaMapper;
    }

    @Override
    public List<CitaDto> obtenerTodo() {
        return this.citaMapper.toDtos(this.citaCrud.findAll());
    }

    @Override
    public List<CitaDto> buscarPorCliente(Long clienteId) {
        return this.citaMapper.toDtos(this.citaCrud.findByClienteId(clienteId));
    }

    @Override
    public List<CitaDto> buscarPorFecha(LocalDateTime fecha) {
        return this.citaMapper.toDtos(this.citaCrud.findByFechaHora(fecha));
    }

    @Override
    public CitaDto buscarPorId(Long codigo) {
        CitaEntity citaEntity = this.citaCrud.findById(codigo).orElseThrow(() -> new CitaNoExisteException(codigo));
        return this.citaMapper.toDto(citaEntity);
    }

    @Override
    public CitaDto guardarCita(CitaDto citaDto) {
        if (this.citaCrud.findByClienteIdAndFechaHora(citaDto.cliente_id(), citaDto.fechaHora()) != null) {
            throw new CitaYaExisteException(citaDto.cliente_id(), citaDto.fechaHora());
        }
        CitaEntity citaEntity = this.citaMapper.toEntity(citaDto);
        this.citaCrud.save(citaEntity);
        return this.citaMapper.toDto(citaEntity);
    }

    @Override
    public CitaDto modificarCita(Long codigo, ModCitaDto modCitaDto) {
        CitaEntity citaEntity = this.citaCrud.findById(codigo)
                .orElseThrow(() -> new CitaNoExisteException(codigo));

        citaEntity.setFechaHora(modCitaDto.fechaHora());
        citaEntity.setClienteId(modCitaDto.cliente_id());
        citaEntity.setServicioId(modCitaDto.servicio_id());

        this.citaCrud.save(citaEntity);
        return this.citaMapper.toDto(citaEntity);
    }

    @Override
    public boolean existsByClienteAndFechaHora(Long clienteId, LocalDateTime fechaHora) {
        return this.citaCrud.findByClienteIdAndFechaHora(clienteId, fechaHora) != null;
    }

    @Override
    public void eliminarCita(Long codigo) {
        CitaEntity citaEntity = this.citaCrud.findById(codigo).orElseThrow(() -> new CitaNoExisteException(codigo));
        this.citaCrud.delete(citaEntity);
    }
}
