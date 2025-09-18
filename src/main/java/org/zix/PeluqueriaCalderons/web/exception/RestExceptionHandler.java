package org.zix.PeluqueriaCalderons.web.exception;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.zix.PeluqueriaCalderons.dominio.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zix.PeluqueriaCalderons.dominio.exception.Error;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ClienteYaExisteException.class)
    public ResponseEntity<Error> handleException(ClienteYaExisteException ex){
        Error error = new Error("cliente_ya_existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ClienteNoExisteException.class)
    public ResponseEntity<Error> handleException(ClienteNoExisteException ex){
        Error error = new Error("cliente_no_existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(EmpleadoYaExistsException.class)
    public ResponseEntity<Error> handleException(EmpleadoYaExistsException ex){
        Error error = new Error("empleado_ya_existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(EmpleadoNoExistsException.class)
    public ResponseEntity<Error> handleException(EmpleadoNoExistsException ex){
        Error error = new Error("empleado_no_existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(UsuarioNoExisteException.class)
    public ResponseEntity<Error> handleException(UsuarioNoExisteException ex){
        Error error = new Error("usuario_no_existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}





