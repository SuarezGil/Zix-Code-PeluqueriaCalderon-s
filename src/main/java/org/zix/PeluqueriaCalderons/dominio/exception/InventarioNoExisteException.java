package org.zix.PeluqueriaCalderons.dominio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InventarioNoExisteException extends RuntimeException {

    public InventarioNoExisteException() {
        super("Producto de inventario no encontrado");
    }

    public InventarioNoExisteException(String mensaje) {
        super(mensaje);
    }

    public InventarioNoExisteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public InventarioNoExisteException(Long id) {
        super("Producto de inventario con ID " + id + " no encontrado");
    }

    public InventarioNoExisteException(String nombre, String tipo) {
        super("Producto de inventario con " + tipo + " '" + nombre + "' no encontrado");
    }
}