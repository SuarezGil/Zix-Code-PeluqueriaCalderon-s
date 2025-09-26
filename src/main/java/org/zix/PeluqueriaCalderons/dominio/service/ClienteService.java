package org.zix.PeluqueriaCalderons.dominio.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.zix.PeluqueriaCalderons.dominio.dto.ClienteDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModClienteDto;
import org.zix.PeluqueriaCalderons.dominio.exception.ClienteYaExisteException;
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

    public ClienteDto obtenerClientePorCodigo(Long codigoCliente){
        return this.clienteRepository.obtenerClientePorCodigo(codigoCliente);
    }

    public ClienteDto guardarCliente(ClienteDto clienteDto) throws ClienteYaExisteException {
        return this.clienteRepository.guardarCliente(clienteDto);
    }

    public ClienteDto modificarCliente(Long codigoCliente, ModClienteDto modClienteDto) throws ClienteYaExisteException {
        return this.clienteRepository.modificarCliente(codigoCliente, modClienteDto);
    }

    public void eliminarCliente(Long codigoCliente) {
        try {
            this.clienteRepository.eliminarCliente(codigoCliente);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("No se puede eliminar el cliente porque tiene citas asociadas.");
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el cliente: " + e.getMessage());
        }
    }
}