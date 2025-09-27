package org.zix.PeluqueriaCalderons.web.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.zix.PeluqueriaCalderons.dominio.dto.InventarioDto;

@Named("inventarioBean")
@ViewScoped
public class InventarioBean implements Serializable {

    private List<InventarioDto> inventarios;
    private InventarioDto inventarioActual;
    private InventarioDto inventarioSeleccionado;

    private final String API_URL = "http://localhost:8090/inventario";
    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        cargarInventarios();
        if (inventarioActual == null) {
            inventarioActual = new InventarioDto();
        }
    }

    public void cargarInventarios() {
        InventarioDto[] lista = restTemplate.getForObject(API_URL, InventarioDto[].class);
        inventarios = Arrays.asList(lista);
    }

    public void prepararNuevo() {
        inventarioActual = new InventarioDto();
    }

    public void prepararEdicion(InventarioDto inv) {
        if (inv != null) {
            inventarioActual = inv;
        } else {
            inventarioActual = new InventarioDto();
        }
    }

    public void guardarInventario() {
        if (inventarioActual.getId() == null) {
            restTemplate.postForObject(API_URL, inventarioActual, InventarioDto.class);
        } else {
            restTemplate.put(API_URL + "/" + inventarioActual.getId(), inventarioActual);
        }
        cargarInventarios();
    }

    public void eliminarInventario(Long id) {
        restTemplate.delete(API_URL + "/" + id);
        cargarInventarios();
    }

    // Getters y Setters
    public List<InventarioDto> getInventarios() { return inventarios; }
    public InventarioDto getInventarioActual() { return inventarioActual; }
    public void setInventarioActual(InventarioDto inventarioActual) { this.inventarioActual = inventarioActual; }
    public InventarioDto getInventarioSeleccionado() { return inventarioSeleccionado; }
    public void setInventarioSeleccionado(InventarioDto inventarioSeleccionado) { this.inventarioSeleccionado = inventarioSeleccionado; }
}
