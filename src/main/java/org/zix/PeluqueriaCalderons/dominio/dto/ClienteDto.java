package org.zix.PeluqueriaCalderons.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import java.util.Date;

public class ClienteDto {

    private Long codigoCliente;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String name;

    @NotBlank(message = "El telefono del cliente es obligatorio")
    private String tel;

    @NotBlank(message = "El correo del cliente es obligatorio")
    private String email;

    @PastOrPresent(message = "La fecha de registro debe ser anterior a la fecha actual")
    private Date registrationDate;

    public ClienteDto(Long codigoCliente, String name, String tel, String email, Date registrationDate) {
        this.codigoCliente = codigoCliente;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    // Getters
    public Long getCodigoCliente() { return codigoCliente; }
    public String getName() { return name; }
    public String getTel() { return tel; }
    public String getEmail() { return email; }
    public Date getRegistrationDate() { return registrationDate; }

    // Setters
    public void setCodigoCliente(Long codigoCliente) { this.codigoCliente = codigoCliente; }
    public void setName(String name) { this.name = name; }
    public void setTel(String tel) { this.tel = tel; }
    public void setEmail(String email) { this.email = email; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }
}
