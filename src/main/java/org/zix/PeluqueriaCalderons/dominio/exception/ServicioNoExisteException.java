package org.zix.PeluqueriaCalderons.dominio.exception;

public class ServicioNoExisteException extends RuntimeException {
    public ServicioNoExisteException(Long codigo) {

        super("El servicio con el codigo " + codigo + " no existe");
    }
}
