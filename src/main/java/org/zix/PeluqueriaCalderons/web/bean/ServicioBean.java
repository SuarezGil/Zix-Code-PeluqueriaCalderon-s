package org.zix.PeluqueriaCalderons.web.bean;

import org.zix.PeluqueriaCalderons.dominio.dto.ServicioDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModServicioDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named("servicioBean")
@ViewScoped
public class ServicioBean implements Serializable {

    private List<ServicioDto> servicios;
    private ServicioDto servicioActual;
    private ServicioDto servicioSeleccionado;

    private final String API_URL = "http://localhost:8090/servicios/v1";
    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        cargarServicios();
        if (servicioActual == null) {
            servicioActual = new ServicioDto(null, null, null); // Inicialización para evitar null
        }
    }

    public void cargarServicios() {
        try {
            ServicioDto[] lista = restTemplate.getForObject(API_URL, ServicioDto[].class);
            System.out.println(Arrays.toString(lista));  // Esto te permitirá ver los datos en la consola
            servicios = Arrays.asList(lista);
        } catch (Exception e) {
            e.printStackTrace();  // Esto te dará más detalles sobre el error
        }
    }


    public void prepararNuevo() {
        servicioActual = new ServicioDto(null, null, null); // Preparar para un nuevo servicio
    }

    public void prepararEdicion(ServicioDto servicio) {
        if (servicio != null) {
            servicioActual = servicio;
        } else {
            servicioActual = new ServicioDto(null, null, null);
        }
    }

    public void guardarServicio() {
        if (servicioActual.name() != null) { // Verificación simple
            if (servicioActual.name() != null) {
                // Si no tiene código, es nuevo, se usa POST
                if (servicioActual.name() == null) {
                    restTemplate.postForObject(API_URL, servicioActual, ServicioDto.class);
                } else {
                    // Si tiene código, es una modificación, se usa PUT
                    ModServicioDto modServicioDto = new ModServicioDto(
                            servicioActual.name(),
                            servicioActual.price(),
                            servicioActual.duration()
                    );
                    restTemplate.put(API_URL + "/" + servicioActual.name(), modServicioDto);
                }
            }
        }
        cargarServicios(); // Recargar servicios
    }

    public void eliminarServicio(Long codigo) {
        restTemplate.delete(API_URL + "/" + codigo);
        cargarServicios(); // Recargar lista después de eliminar
    }

    // Getters y Setters
    public List<ServicioDto> getServicios() {
        return servicios;
    }

    public ServicioDto getServicioActual() {
        return servicioActual;
    }

    public void setServicioActual(ServicioDto servicioActual) {
        this.servicioActual = servicioActual;
    }

    public ServicioDto getServicioSeleccionado() {
        return servicioSeleccionado;
    }

    public void setServicioSeleccionado(ServicioDto servicioSeleccionado) {
        this.servicioSeleccionado = servicioSeleccionado;
    }
}
