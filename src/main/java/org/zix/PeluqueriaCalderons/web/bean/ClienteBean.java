package org.zix.PeluqueriaCalderons.web.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.springframework.web.client.RestTemplate;
import org.zix.PeluqueriaCalderons.dominio.dto.ClienteDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Named("clienteBean")
@RequestScoped
public class ClienteBean implements Serializable {

    private List<ClienteDto> clientes;
    private ClienteDto clienteSeleccionado;
    private ClienteDto clienteActual;

    private final RestTemplate restTemplate;
    private static final String API_URL = "http://localhost:8090/clientes";

    public ClienteBean() {
        this.restTemplate = new RestTemplate();
        cargarClientes();
    }

    // Cargar todos los clientes
    public void cargarClientes() {
        ClienteDto[] response = restTemplate.getForObject(API_URL, ClienteDto[].class);
        clientes = Arrays.asList(response);
    }

    // Preparar para agregar un nuevo cliente
    public void prepararNuevo() {
        clienteActual = new ClienteDto(null, "", "", "", LocalDate.now());
    }

    // Preparar para editar el cliente seleccionado
    public void prepararEditar() {
        clienteActual = clienteSeleccionado;
    }

    // Guardar cliente (POST o PUT)
    public void guardarCliente() {
        if (clienteActual.codigoCliente() == null) { // <-- record usa .codigoCliente()
            restTemplate.postForObject(API_URL, clienteActual, ClienteDto.class);
        } else {
            restTemplate.put(API_URL + "/" + clienteActual.codigoCliente(), clienteActual);
        }
        cargarClientes();
    }

    // Eliminar cliente
    public void eliminarCliente() {
        if (clienteSeleccionado != null) {
            restTemplate.delete(API_URL + "/" + clienteSeleccionado.codigoCliente());
            cargarClientes();
        }
    }

    // Getters y Setters
    public List<ClienteDto> getClientes() { return clientes; }
    public ClienteDto getClienteSeleccionado() { return clienteSeleccionado; }
    public void setClienteSeleccionado(ClienteDto clienteSeleccionado) { this.clienteSeleccionado = clienteSeleccionado; }
    public ClienteDto getClienteActual() { return clienteActual; }
    public void setClienteActual(ClienteDto clienteActual) { this.clienteActual = clienteActual; }
}
