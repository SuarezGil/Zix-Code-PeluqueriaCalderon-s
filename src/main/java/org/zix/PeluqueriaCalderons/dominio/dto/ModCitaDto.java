package org.zix.PeluqueriaCalderons.dominio.dto;

import java.io.Serializable;
import java.util.Date;

public class ModCitaDto implements Serializable {
    private Date dateTime;
    private Long clienteId;
    private Long servicioId;

    public ModCitaDto() {}

    // Getters y setters (no métodos con paréntesis vacíos)
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }
}