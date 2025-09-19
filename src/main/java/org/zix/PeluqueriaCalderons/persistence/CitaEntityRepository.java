package org.zix.PeluqueriaCalderons.persistence;


import org.springframework.stereotype.Repository;
import org.zix.PeluqueriaCalderons.dominio.dto.CitaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModCitaDto;
import org.zix.PeluqueriaCalderons.dominio.exception.CitaNoExisteException;
import org.zix.PeluqueriaCalderons.dominio.exception.CitaYaExisteException;
import org.zix.PeluqueriaCalderons.dominio.repository.CitaRepository;
import org.zix.PeluqueriaCalderons.persistence.crud.CrudCitaEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.CitaEntity;
import org.zix.PeluqueriaCalderons.web.mapper.CitaMapper;

import java.util.List;

@Repository
public class CitaEntityRepository implements CitaRepository {

    private final CrudCitaEntity crudCita;
    private final CitaMapper citaMapper;

    public CitaEntityRepository(CrudCitaEntity crudCita, CitaMapper citaMapper) {
        this.crudCita = crudCita;
        this.citaMapper = citaMapper;
    }

    @Override
    public List<CitaDto> obtenerTodo() {
        return this.citaMapper.toDto(this.crudCita.findAll());
    }



    @Override
    public CitaDto buscarCitaPorCodigo(Long codigo) {
        CitaEntity cita = this.crudCita.findById(codigo)
                .orElseThrow(() -> new CitaNoExisteException(codigo));
        return this.citaMapper.toDto(cita);
    }

    @Override
    public CitaDto guardarCita(ModCitaDto modCitaDto) {
        CitaEntity cita = this.citaMapper.toEntity(modCitaDto);
        if (this.crudCita.findFirstByFechaHora(modCitaDto.dateTime()).isPresent()){
            throw new CitaYaExisteException(modCitaDto.dateTime());
        }
        // Guardar en la DB con JPA
        this.crudCita.save(cita);
        // Retornar como DTO
        return this.citaMapper.toDto(cita);
    }

    @Override
    public CitaDto modificarCita(Long codigo, ModCitaDto modCitaDto) {
        CitaEntity cita = this.crudCita.findById(codigo).orElse(null);
        if (cita == null) {
            throw new CitaNoExisteException(codigo);
        } else {
            this.citaMapper.modificarEntityFromDto(modCitaDto, cita);
            return this.citaMapper.toDto(this.crudCita.save(cita));
        }
    }

    @Override
    public void eliminarCita(Long codigo) {
        CitaEntity cita = this.crudCita.findById(codigo).orElse(null);
        if (cita == null) {
            throw new CitaNoExisteException(codigo);
        } else {
            this.crudCita.deleteById(codigo);
        }
    }
}
