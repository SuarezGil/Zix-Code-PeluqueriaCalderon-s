package org.zix.PeluqueriaCalderons.persistence;

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

        return this.clienteMapper.toDto(this.crudCliente.findAll());
    }

    @Override
    public ClienteDto obtenerClientePorCodigo(Long codigo) {
        ClienteEntity cliente = this.crudCliente.findById(codigo).orElseThrow(()-> new ClienteNoExisteException(codigo));
        return this.clienteMapper.toDto(cliente);
    }

    @Override
    public ClienteDto guardarCliente(ClienteDto clienteDto) {
        ClienteEntity cliente = this.clienteMapper.toEntity(clienteDto);
        if (this.crudCliente.findFirstByCorreo(clienteDto.email())!=null){
            throw new ClienteYaExisteException(clienteDto.email());
        }
        //guardar en la DB con JPA
        this.crudCliente.save(cliente);
        //Retornar el valor guardado como DTO
        return this.clienteMapper.toDto(cliente);
    }

    @Override
    public ClienteDto modificarCliente(Long codigo, ModClienteDto modClienteDto) {
        ClienteEntity cliente = this.crudCliente.findById(codigo).orElse(null);
//        pelicula.setNombre(modPeliculaDto.name());
//        pelicula.setFechaEstreno(modPeliculaDto.releaseDate());
//        pelicula.setCalificacion(BigDecimal.valueOf(modPeliculaDto.rating()));
//        this.crudPelicula.save(pelicula);
//        return this.peliculaMapper.toDto(pelicula);
        if (cliente == null) {throw new ClienteNoExisteException(codigo);
        }
        else{
            this.clienteMapper.modificarEntityFromDto(modClienteDto, cliente);
            return this.clienteMapper.toDto(this.crudCliente.save(cliente));
        }    }

    @Override
    public void eliminarCliente(Long codigo) {
    ClienteEntity clienteEntity = this.crudCliente.findById(codigo).orElse(null);
    if (clienteEntity == null) {throw new ClienteNoExisteException(codigo);
    }else {
        this.crudCliente.deleteById(codigo);
    }
    }
}
