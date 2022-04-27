package cl.usuarios.exception;

public class ErrorNegocioException extends Exception {

    public ErrorNegocioException() {
        super("");
    }

    public ErrorNegocioException(String message) {
        super(message);
    }
}
