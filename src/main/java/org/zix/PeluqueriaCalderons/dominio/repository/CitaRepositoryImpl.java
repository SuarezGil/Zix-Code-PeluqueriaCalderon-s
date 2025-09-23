package org.zix.PeluqueriaCalderons.dominio.repository;

import org.springframework.stereotype.Repository;
import org.zix.PeluqueriaCalderons.dominio.dto.CitaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModCitaDto;
import org.zix.PeluqueriaCalderons.dominio.repository.CitaRepository;
import org.zix.PeluqueriaCalderons.persistence.crud.CitaCrud;
import org.zix.PeluqueriaCalderons.persistence.entity.CitaEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CitaRepositoryImpl implements CitaRepository {

    private final CitaCrud citaCrud;

    public CitaRepositoryImpl(CitaCrud citaCrud) {
        this.citaCrud = citaCrud;
    }

    @Override
    public List<CitaDto> obtenerTodo() {
        return ((List<CitaEntity>) citaCrud.findAll())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaDto> buscarPorCliente(Long clienteId) {
        return citaCrud.findByClienteId(clienteId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaDto> buscarPorFecha(LocalDateTime fecha) {
        return citaCrud.findByFechaHora(fecha)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CitaDto buscarPorId(Long codigo) {
        return citaCrud.findById(codigo)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public CitaDto guardarCita(CitaDto citaDto) {
        CitaEntity entity = toEntity(citaDto);
        entity = citaCrud.save(entity);
        return toDto(entity);
    }

    @Override
    public void eliminarCita(Long codigo) {
        citaCrud.deleteById(codigo);
    }

    @Override
    public CitaDto modificarCita(Long codigo, ModCitaDto citaDto) {
        return citaCrud.findById(codigo)
                .map(entity -> {
                    entity.setFechaHora(citaDto.fechaHora());
                    entity.setClienteId(citaDto.cliente_id());
                    entity.setServicioId(citaDto.servicio_id());
                    entity = citaCrud.save(entity);
                    return toDto(entity);
                })
                .orElse(null);
    }

    @Override
    public boolean existsByClienteAndFechaHora(Long clienteId, LocalDateTime fechaHora) {
        return citaCrud.findByClienteIdAndFechaHora(clienteId, fechaHora) != null;
    }

    private CitaDto toDto(CitaEntity entity) {
        return new CitaDto(
                entity.getCodigo(),
                entity.getFechaHora(),
                entity.getClienteId(),
                entity.getServicioId()
        );
    }

    private CitaEntity toEntity(CitaDto dto) {
        CitaEntity entity = new CitaEntity();
        entity.setCodigo(dto.codigo());
        entity.setFechaHora(dto.fechaHora());
        entity.setClienteId(dto.cliente_id());
        entity.setServicioId(dto.servicio_id());
        return entity;
    }
}
