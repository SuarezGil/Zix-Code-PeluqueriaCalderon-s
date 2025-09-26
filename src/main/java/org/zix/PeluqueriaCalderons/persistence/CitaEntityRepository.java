package org.zix.PeluqueriaCalderons.persistence;

import org.springframework.stereotype.Repository;
import org.zix.PeluqueriaCalderons.dominio.dto.CitaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModCitaDto;
import org.zix.PeluqueriaCalderons.dominio.exception.CitaNoExisteException;
import org.zix.PeluqueriaCalderons.dominio.exception.CitaYaExisteException;
import org.zix.PeluqueriaCalderons.dominio.repository.CitaRepository;
import org.zix.PeluqueriaCalderons.persistence.crud.CrudCitaEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.CitaEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.ClienteEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.ServicioEntity;
import org.zix.PeluqueriaCalderons.web.mapper.CitaMapper;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        List<CitaEntity> citas = (List<CitaEntity>) this.crudCita.findAll();
        return this.citaMapper.toDto(citas);
    }

    @Override
    public CitaDto buscarCitaPorCodigo(Long codigo) {
        CitaEntity cita = this.crudCita.findById(codigo)
                .orElseThrow(() -> new CitaNoExisteException(codigo));
        return this.citaMapper.toDto(cita);
    }

    @Override
    public CitaDto guardarCita(ModCitaDto modCitaDto) {
        // Convertir Date a LocalDateTime si es necesario
        LocalDateTime fechaHora = convertirDateALocalDateTime(modCitaDto.getDateTime());

        // Verificar si ya existe una cita en la misma fecha/hora
        Optional<CitaEntity> citaExistente = this.crudCita.findByFechaHora(fechaHora);
        if (citaExistente.isPresent()) {
            throw new CitaYaExisteException(fechaHora);
        }

        CitaEntity cita = new CitaEntity();
        cita.setFechaHora(fechaHora);

        // Crear entidades de referencia solo con el ID
        ClienteEntity cliente = new ClienteEntity();
        cliente.setCodigoCliente(modCitaDto.getClienteId());
        cita.setCliente(cliente);

        ServicioEntity servicio = new ServicioEntity();
        servicio.setCodigoServicio(modCitaDto.getServicioId());
        cita.setServicio(servicio);

        // Guardar y retornar
        CitaEntity citaGuardada = this.crudCita.save(cita);
        return this.citaMapper.toDto(citaGuardada);
    }

    @Override
    public CitaDto modificarCita(Long codigo, ModCitaDto modCitaDto) {
        CitaEntity cita = this.crudCita.findById(codigo)
                .orElseThrow(() -> new CitaNoExisteException(codigo));

        // Convertir Date a LocalDateTime
        LocalDateTime fechaHora = convertirDateALocalDateTime(modCitaDto.getDateTime());

        // Verificar si la nueva fecha/hora ya existe (excluyendo la cita actual)
        Optional<CitaEntity> citaExistente = this.crudCita.findByFechaHoraAndCodigoNot(fechaHora, codigo);
        if (citaExistente.isPresent()) {
            throw new CitaYaExisteException(fechaHora);
        }

        // Actualizar fecha, cliente y servicio
        cita.setFechaHora(fechaHora);

        ClienteEntity cliente = new ClienteEntity();
        cliente.setCodigoCliente(modCitaDto.getClienteId());
        cita.setCliente(cliente);

        ServicioEntity servicio = new ServicioEntity();
        servicio.setCodigoServicio(modCitaDto.getServicioId());
        cita.setServicio(servicio);

        CitaEntity citaActualizada = this.crudCita.save(cita);
        return this.citaMapper.toDto(citaActualizada);
    }

    @Override
    public void eliminarCita(Long codigo) {
        if (!this.crudCita.existsById(codigo)) {
            throw new CitaNoExisteException(codigo);
        }
        this.crudCita.deleteById(codigo);
    }

    // Método auxiliar para convertir Date a LocalDateTime
    private LocalDateTime convertirDateALocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), java.time.ZoneId.systemDefault());
    }
}