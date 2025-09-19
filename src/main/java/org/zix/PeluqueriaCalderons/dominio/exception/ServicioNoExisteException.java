package org.zix.PeluqueriaCalderons.dominio.exception;

public class ServicioNoExisteException extends RuntimeException {
    public ServicioNoExisteException(Long codigoServicio) {

        super("El servicio con el codigo " + codigoServicio + " no existe");
    }
}
