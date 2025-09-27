package org.zix.PeluqueriaCalderons.web.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.zix.PeluqueriaCalderons.dominio.dto.EmpleadoDto;

@Named("empleadoBean")
@ViewScoped
public class EmpleadoBean implements Serializable {

    private List<EmpleadoDto> empleados;
    private EmpleadoDto empleadoActual;
    private EmpleadoDto empleadoSeleccionado;

    private final String API_URL = "http://localhost:8090/empleados";
    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        cargarEmpleados();
        if (empleadoActual == null) {
            empleadoActual = new EmpleadoDto();
        }
    }

    public void cargarEmpleados() {
        EmpleadoDto[] lista = restTemplate.getForObject(API_URL, EmpleadoDto[].class);
        empleados = Arrays.asList(lista);
    }

    public void prepararNuevo() {
        empleadoActual = new EmpleadoDto(); // ⚠ Siempre inicializamos para que no sea null
    }

    public void prepararEdicion(EmpleadoDto emp) {
        if (emp != null) {
            empleadoActual = emp;
        } else {
            empleadoActual = new EmpleadoDto();
        }
    }

    public void guardarEmpleado() {
        if (empleadoActual.getId() == null) {
            restTemplate.postForObject(API_URL, empleadoActual, EmpleadoDto.class);
        } else {
            restTemplate.put(API_URL + "/" + empleadoActual.getId(), empleadoActual);
        }
        cargarEmpleados();
    }

    public void eliminarEmpleado(Long id) {
        restTemplate.delete(API_URL + "/" + id);
        cargarEmpleados();
    }

    // Getters y Setters
    public List<EmpleadoDto> getEmpleados() { return empleados; }
    public EmpleadoDto getEmpleadoActual() { return empleadoActual; }
    public void setEmpleadoActual(EmpleadoDto empleadoActual) { this.empleadoActual = empleadoActual; }
    public EmpleadoDto getEmpleadoSeleccionado() { return empleadoSeleccionado; }
    public void setEmpleadoSeleccionado(EmpleadoDto empleadoSeleccionado) { this.empleadoSeleccionado = empleadoSeleccionado; }
}
