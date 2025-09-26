package org.zix.PeluqueriaCalderons.persistence;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.zix.PeluqueriaCalderons.dominio.dto.ClienteDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModClienteDto;
import org.zix.PeluqueriaCalderons.dominio.exception.ClienteNoExisteException;
import org.zix.PeluqueriaCalderons.dominio.exception.ClienteYaExisteException;
import org.zix.PeluqueriaCalderons.dominio.repository.ClienteRepository;
import org.zix.PeluqueriaCalderons.persistence.crud.CrudClienteEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.ClienteEntity;
import org.zix.PeluqueriaCalderons.web.mapper.ClienteMapper;

import java.util.List;

@Repository
public class ClienteEntityRepository implements ClienteRepository {

    private final CrudClienteEntity crudCliente;
    private final ClienteMapper clienteMapper;

    public ClienteEntityRepository(CrudClienteEntity crudCliente, ClienteMapper clienteMapper) {
        this.crudCliente = crudCliente;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public List<ClienteDto> obtenerTodo() {
        List<ClienteEntity> entities = (List<ClienteEntity>) this.crudCliente.findAll();
        return this.clienteMapper.toDto(entities);
    }

    @Override
    public ClienteDto obtenerClientePorCodigo(Long codigoCliente) {
        ClienteEntity cliente = this.crudCliente.findById(codigoCliente)
                .orElseThrow(() -> new ClienteNoExisteException(codigoCliente));
        return this.clienteMapper.toDto(cliente);
    }

    @Override
    public ClienteDto guardarCliente(ClienteDto clienteDto) {
        // Verificar si ya existe un cliente con el mismo correo
        ClienteEntity clienteExistente = this.crudCliente.findFirstByCorreo(clienteDto.getEmail());
        if (clienteExistente != null) {
            throw new ClienteYaExisteException(clienteDto.getEmail());
        }

        ClienteEntity cliente = this.clienteMapper.toEntity(clienteDto);
        ClienteEntity clienteGuardado = this.crudCliente.save(cliente);
        return this.clienteMapper.toDto(clienteGuardado);
    }

    @Override
    public ClienteDto modificarCliente(Long codigoCliente, ModClienteDto modClienteDto) {
        ClienteEntity cliente = this.crudCliente.findById(codigoCliente)
                .orElseThrow(() -> new ClienteNoExisteException(codigoCliente));

        this.clienteMapper.modificarEntityFromDto(modClienteDto, cliente);
        ClienteEntity clienteActualizado = this.crudCliente.save(cliente);
        return this.clienteMapper.toDto(clienteActualizado);
    }

    @Override
    public void eliminarCliente(Long codigoCliente) {
        if (!this.crudCliente.existsById(codigoCliente)) {
            throw new ClienteNoExisteException(codigoCliente);
        }

        try {
            this.crudCliente.deleteById(codigoCliente);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("No se puede eliminar el cliente porque tiene citas asociadas.");
        }
    }
}