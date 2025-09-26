package org.zix.PeluqueriaCalderons.web.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.web.client.RestTemplate;
import org.zix.PeluqueriaCalderons.dominio.dto.FacturaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModFacturaDto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named("facturaBean")
@ViewScoped
public class FacturaBean implements Serializable {

    private List<FacturaDto> facturas;
    private FacturaDto facturaActual;
    private FacturaDto facturaSeleccionada;

    private final String API_URL = "http://localhost:8090/facturas/v1";
    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        cargarFacturas();
        if (facturaActual == null) {
            facturaActual = new FacturaDto(null, null, null, null, null); // Initialize facturaActual to avoid NullPointerException
        }
    }

    public void cargarFacturas() {
        // Obtiene las facturas de la API
        FacturaDto[] lista = restTemplate.getForObject(API_URL, FacturaDto[].class);
        facturas = Arrays.asList(lista);
    }

    public void prepararNuevo() {
        // Inicializa una nueva factura
        facturaActual = new FacturaDto(null, null, null, null, null);
    }

    public void prepararEdicion(FacturaDto factura) {
        // Prepara la factura para la edición
        if (factura != null) {
            facturaActual = factura;
        } else {
            facturaActual = new FacturaDto(null, null, null, null, null);
        }
    }

    public void guardarFactura() {
        // Guarda la factura, ya sea nueva o modificada
        if (facturaActual.codigo() == null) {
            // Si no tiene código, es una nueva factura
            FacturaDto nuevaFactura = restTemplate.postForObject(API_URL, facturaActual, FacturaDto.class);
        } else {
            // Si tiene código, es una factura existente
            ModFacturaDto modFacturaDto = new ModFacturaDto(
                    facturaActual.fecha(),
                    facturaActual.total(),
                    facturaActual.cliente_id(),
                    facturaActual.empleado_id()
            );
            restTemplate.put(API_URL + "/" + facturaActual.codigo(), modFacturaDto);
        }

        // Después de guardar o modificar, recarga las facturas
        cargarFacturas();
    }

    public void eliminarFactura(Long codigo) {
        // Elimina la factura según el código
        restTemplate.delete(API_URL + "/" + codigo);
        cargarFacturas(); // Recarga la lista de facturas después de eliminar
    }

    // Getters y Setters
    public List<FacturaDto> getFacturas() {
        return facturas;
    }

    public FacturaDto getFacturaActual() {
        return facturaActual;
    }

    public void setFacturaActual(FacturaDto facturaActual) {
        this.facturaActual = facturaActual;
    }

    public FacturaDto getFacturaSeleccionada() {
        return facturaSeleccionada;
    }

    public void setFacturaSeleccionada(FacturaDto facturaSeleccionada) {
        this.facturaSeleccionada = facturaSeleccionada;
    }
}
