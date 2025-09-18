package org.zix.PeluqueriaCalderons.dominio.exception;

public class ServicioYaExisteException extends RuntimeException {
    public ServicioYaExisteException(String nombre) {

        super("Ya existe un servicio con el nombre: " + nombre);
    }
}
