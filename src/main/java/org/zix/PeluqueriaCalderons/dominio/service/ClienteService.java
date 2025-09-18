package org.zix.PeluqueriaCalderons.dominio.service;

import org.springframework.stereotype.Service;
import org.zix.PeluqueriaCalderons.dominio.dto.ClienteDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModClienteDto;
import org.zix.PeluqueriaCalderons.dominio.repository.ClienteRepository;

import java.util.List;


@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteDto> obtenerTodo(){
        return this.clienteRepository.obtenerTodo();
    }

    public ClienteDto obtenerClientePorCodigo(Long codigo){
        return this.clienteRepository.obtenerClientePorCodigo(codigo);
    }

    public ClienteDto guardarCliente(ClienteDto clienteDto){
        return this.clienteRepository.guardarCliente(clienteDto);
    }

    public ClienteDto modificarCliente(Long codigo, ModClienteDto modClienteDto){
    return this.clienteRepository.modificarCliente(codigo, modClienteDto);
    }

    public void eliminarCliente(Long codigo){
        this.clienteRepository.eliminarCliente(codigo);
    }
}
