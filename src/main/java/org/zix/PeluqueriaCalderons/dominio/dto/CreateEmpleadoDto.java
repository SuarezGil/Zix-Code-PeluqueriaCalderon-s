package org.zix.PeluqueriaCalderons.dominio.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class CreateEmpleadoDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    @Size(message = "El email no puede exceder 100 caracteres")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size( message = "El formato del teléfono no es válido")
    private String telefono;

    @NotBlank(message = "El puesto es obligatorio")
    @Size(min = 2, max = 50, message = "El puesto debe tener entre 2 y 50 caracteres")
    private String puesto;

    @NotNull(message = "La fecha de contratación es obligatoria")
    @PastOrPresent(message = "La fecha de contratación no puede ser futura")
    private LocalDate fechaContratacion;

    // Constructores, getters y setters
    public CreateEmpleadoDto() {}

    public CreateEmpleadoDto(String nombre, String email, String telefono, String puesto, LocalDate fechaContratacion) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.puesto = puesto;
        this.fechaContratacion = fechaContratacion;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) { this.puesto = puesto; }

    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }
}
