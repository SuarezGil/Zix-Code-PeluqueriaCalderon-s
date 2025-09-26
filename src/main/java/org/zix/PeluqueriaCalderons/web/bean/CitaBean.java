package org.zix.PeluqueriaCalderons.web.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.zix.PeluqueriaCalderons.dominio.dto.CitaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModCitaDto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named("citaBean")
@ViewScoped
public class CitaBean implements Serializable {

    private List<CitaDto> citas;
    private CitaDto citaActual;
    private CitaDto citaSeleccionada;

    private final String API_URL = "http://localhost:8090/citas";
    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        cargarCitas();
        citaActual = new CitaDto();
    }

    public void cargarCitas() {
        try {
            CitaDto[] lista = restTemplate.getForObject(API_URL, CitaDto[].class);
            if (lista != null) {
                citas = Arrays.asList(lista);
            }
        } catch (Exception e) {
            mostrarMensaje("Error al cargar citas: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
            e.printStackTrace();
        }
    }

    public void prepararNuevo() {
        citaActual = new CitaDto();
        citaSeleccionada = null;
    }

    public void prepararEdicion(CitaDto cita) {
        if (cita != null) {
            citaActual = cita;
            citaSeleccionada = cita;
        } else {
            prepararNuevo();
        }
    }

    public void guardarCita() {
        try {
            if (citaActual.getId() == null) {
                // Crear nueva cita
                ModCitaDto modCita = new ModCitaDto();
                modCita.setDateTime(citaActual.getDateTime());
                modCita.setClienteId(citaActual.getClienteId());
                modCita.setServicioId(citaActual.getServicioId());

                restTemplate.postForObject(API_URL, modCita, CitaDto.class);
                mostrarMensaje("Cita creada exitosamente", FacesMessage.SEVERITY_INFO);
            } else {
                // Actualizar cita existente
                ModCitaDto modCita = new ModCitaDto();
                modCita.setDateTime(citaActual.getDateTime());
                modCita.setClienteId(citaActual.getClienteId());
                modCita.setServicioId(citaActual.getServicioId());

                restTemplate.put(API_URL + "/" + citaActual.getId(), modCita);
                mostrarMensaje("Cita actualizada exitosamente", FacesMessage.SEVERITY_INFO);
            }
            cargarCitas();
        } catch (Exception e) {
            mostrarMensaje("Error al guardar cita: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
            e.printStackTrace();
        }
    }

    public void eliminarCita(Long id) {
        try {
            restTemplate.delete(API_URL + "/" + id);
            mostrarMensaje("Cita eliminada exitosamente", FacesMessage.SEVERITY_INFO);
            cargarCitas();
        } catch (Exception e) {
            mostrarMensaje("Error al eliminar cita: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
            e.printStackTrace();
        }
    }

    private void mostrarMensaje(String mensaje, FacesMessage.Severity severidad) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(severidad, mensaje, null));
    }

    // Getters y Setters
    public List<CitaDto> getCitas() {
        return citas;
    }

    public void setCitas(List<CitaDto> citas) {
        this.citas = citas;
    }

    public CitaDto getCitaActual() {
        return citaActual;
    }

    public void setCitaActual(CitaDto citaActual) {
        this.citaActual = citaActual;
    }

    public CitaDto getCitaSeleccionada() {
        return citaSeleccionada;
    }

    public void setCitaSeleccionada(CitaDto citaSeleccionada) {
        this.citaSeleccionada = citaSeleccionada;
    }
}