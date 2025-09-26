package org.zix.PeluqueriaCalderons.dominio.dto;

import java.io.Serializable;
import java.util.Date;

public class CitaDto implements Serializable {
    private Long codigo;
    private Date dateTime;
    private Long clienteId;
    private Long servicioId;

    public CitaDto() {}

    // Getter y setter para id (compatibilidad con el bean)
    public Long getId() {
        return codigo;
    }

    public void setId(Long id) {
        this.codigo = id;
    }

    // Getters y setters normales
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

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