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
        if (this.crudCita.findFirstByFechaHora(modCitaDto.dateTime()).isPresent()) {
            throw new CitaYaExisteException(modCitaDto.dateTime());
        }

        CitaEntity cita = new CitaEntity();
        cita.setFechaHora(modCitaDto.dateTime());

        // Crear entidades de referencia solo con el ID
        ClienteEntity cliente = new ClienteEntity();
        cliente.setCodigoCliente(modCitaDto.clienteId());
        cita.setCliente(cliente);

        ServicioEntity servicio = new ServicioEntity();
        servicio.setCodigoServicio(modCitaDto.servicioId());
        cita.setServicio(servicio);

        // Guardar y retornar
        this.crudCita.save(cita);
        return this.citaMapper.toDto(cita);
    }

    @Override
    public CitaDto modificarCita(Long codigo, ModCitaDto modCitaDto) {
        CitaEntity cita = this.crudCita.findById(codigo).orElseThrow(() -> new CitaNoExisteException(codigo));

        // Actualizar fecha, cliente y servicio
        cita.setFechaHora(modCitaDto.dateTime());

        ClienteEntity cliente = new ClienteEntity();
        cliente.setCodigoCliente(modCitaDto.clienteId());
        cita.setCliente(cliente);

        ServicioEntity servicio = new ServicioEntity();
        servicio.setCodigoServicio(modCitaDto.servicioId());
        cita.setServicio(servicio);

        return this.citaMapper.toDto(this.crudCita.save(cita));
    }

    @Override
    public void eliminarCita(Long codigo) {
        CitaEntity cita = this.crudCita.findById(codigo)
                .orElseThrow(() -> new CitaNoExisteException(codigo));
        this.crudCita.deleteById(codigo);
    }
}
