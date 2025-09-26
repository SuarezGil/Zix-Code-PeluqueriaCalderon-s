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
import java.time.LocalDate;
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
        this.clienteNuevo = new ClienteDto(null, "", "", "", LocalDate.now());
    }

    @PostConstruct
    public void init() {
        cargarClientes();
    }

    public void cargarClientes() {
        try {
            clientes = clienteService.obtenerTodo();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al cargar clientes: " + e.getMessage()));
        }
    }

    public void guardarCliente() {
        try {
            clienteService.guardarCliente(clienteNuevo);
            cargarClientes();
            clienteNuevo = new ClienteDto(null, "", "", "", LocalDate.now());
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
                clienteSeleccionado = null;

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
                clienteSeleccionado = null;

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
        if (textoBusqueda == null || textoBusqueda.isBlank()) {
            cargarClientes();
            return;
        }

        try {
            Long codigo = Long.parseLong(textoBusqueda);
            ClienteDto cliente = clienteService.obtenerClientePorCodigo(codigo);
            clientes = List.of(cliente);
        } catch (NumberFormatException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El código debe ser un número válido"));
            cargarClientes();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Cliente no encontrado"));
            clientes = List.of();
        }
    }

    // Getters y Setters
    public List<ClienteDto> getClientes() { return clientes; }
    public ClienteDto getClienteSeleccionado() { return clienteSeleccionado; }
    public void setClienteSeleccionado(ClienteDto clienteSeleccionado) { this.clienteSeleccionado = clienteSeleccionado; }
    public ClienteDto getClienteNuevo() { return clienteNuevo; }
    public void setClienteNuevo(ClienteDto clienteNuevo) { this.clienteNuevo = clienteNuevo; }
    public String getTextoBusqueda() { return textoBusqueda; }
    public void setTextoBusqueda(String textoBusqueda) { this.textoBusqueda = textoBusqueda; }
}