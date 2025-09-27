package org.zix.PeluqueriaCalderons.web.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import org.zix.PeluqueriaCalderons.dominio.dto.ClienteDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModClienteDto;
import org.zix.PeluqueriaCalderons.dominio.exception.ClienteYaExisteException;
import org.zix.PeluqueriaCalderons.dominio.service.ClienteService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {

    @Inject
    private ClienteService clienteService;

    private List<ClienteDto> clientes;
    private ClienteDto clienteSeleccionado;
    private ClienteDto clienteNuevo;
    private String textoBusqueda;

    public ClienteBean() {
        this.clienteNuevo = new ClienteDto(null, "", "", "", new Date());
        this.clienteSeleccionado = new ClienteDto(null, "", "", "", new Date());
        this.clientes = new ArrayList<>();
    }

    @PostConstruct
    public void init() { cargarClientes(); }

    public void cargarClientes() {
        try { clientes = clienteService.obtenerTodo(); }
        catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al cargar clientes: " + e.getMessage()));
        }
    }

    public void guardarCliente() {
        try {
            clienteService.guardarCliente(clienteNuevo);
            cargarClientes();
            clienteNuevo = new ClienteDto(null, "", "", "", new Date());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cliente guardado correctamente."));
        } catch (ClienteYaExisteException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al guardar cliente: " + e.getMessage()));
        }
    }

    public void modificarCliente() {
        if (clienteSeleccionado != null && clienteSeleccionado.getCodigoCliente() != null) {
            try {
                ModClienteDto mod = new ModClienteDto(
                        clienteSeleccionado.getName(),
                        clienteSeleccionado.getTel(),
                        clienteSeleccionado.getEmail(),
                        clienteSeleccionado.getRegistrationDate()
                );
                clienteService.modificarCliente(clienteSeleccionado.getCodigoCliente(), mod);
                cargarClientes();
                clienteSeleccionado = new ClienteDto(null, "", "", "", new Date());
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cliente modificado correctamente."));
            } catch (ClienteYaExisteException e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al modificar cliente: " + e.getMessage()));
            }
        }
    }

    public void eliminarCliente() {
        if (clienteSeleccionado != null && clienteSeleccionado.getCodigoCliente() != null) {
            try {
                clienteService.eliminarCliente(clienteSeleccionado.getCodigoCliente());
                cargarClientes();
                clienteSeleccionado = new ClienteDto(null, "", "", "", new Date());
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cliente eliminado correctamente."));
            } catch (Exception e) {
                String mensaje = e.getMessage();
                if (mensaje != null && mensaje.contains("citas asociadas")) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                                    "No se puede eliminar el cliente porque tiene citas asociadas."));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al eliminar cliente: " + mensaje));
                }
            }
        }
    }

    public void buscarClientePorId() {
        if (textoBusqueda == null || textoBusqueda.isBlank()) { cargarClientes(); return; }
        try {
            Long codigo = Long.parseLong(textoBusqueda);
            ClienteDto cliente = clienteService.obtenerClientePorCodigo(codigo);
            clientes = cliente != null ? List.of(cliente) : new ArrayList<>();
        } catch (NumberFormatException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El código debe ser un número válido"));
            cargarClientes();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Cliente no encontrado"));
            clientes = new ArrayList<>();
        }
    }

    public void seleccionarCliente(ClienteDto c) {
        if (c != null) {
            this.clienteSeleccionado = new ClienteDto(
                    c.getCodigoCliente(),
                    c.getName(),
                    c.getTel(),
                    c.getEmail(),
                    c.getRegistrationDate()
            );
        } else {
            this.clienteSeleccionado = new ClienteDto(null, "", "", "", new Date());
        }
    }

    // Getters y Setters
    public List<ClienteDto> getClientes() { return clientes; }
    public ClienteDto getClienteSeleccionado() {
        if (clienteSeleccionado == null) clienteSeleccionado = new ClienteDto(null, "", "", "", new Date());
        return clienteSeleccionado;
    }
    public void setClienteSeleccionado(ClienteDto clienteSeleccionado) { this.clienteSeleccionado = clienteSeleccionado; }
    public ClienteDto getClienteNuevo() { return clienteNuevo; }
    public void setClienteNuevo(ClienteDto clienteNuevo) { this.clienteNuevo = clienteNuevo; }
    public String getTextoBusqueda() { return textoBusqueda; }
    public void setTextoBusqueda(String textoBusqueda) { this.textoBusqueda = textoBusqueda; }
}
