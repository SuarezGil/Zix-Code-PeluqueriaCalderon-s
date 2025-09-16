package org.zix.PeluqueriaCalderons.dominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.zix.PeluqueriaCalderons.dominio.dto.ClienteDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModClienteDto;
import org.zix.PeluqueriaCalderons.persistence.entity.ClienteEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.UsuarioEntity;

import java.util.List;

public interface ClienteRepository {
    List<ClienteDto> obtenerTodo();
    ClienteDto obtenerClientePorCodigo(Long codigo);
    ClienteDto guardarCliente(ClienteDto clienteDto);
    ClienteDto modificarCliente(Long codigo, ModClienteDto clienteDto);
    void eliminarCliente(Long codigo);
}
