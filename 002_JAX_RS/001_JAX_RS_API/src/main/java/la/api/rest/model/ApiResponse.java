package la.api.rest.model;

import jakarta.ws.rs.core.Response;

public class ApiResponse {
    private boolean success;
    private String message;
    private Object data;
    private String errorCode;

    // Constructor para respuestas exitosas
    public ApiResponse(Object data) {
        this.success = true;
        this.message = "Operación exitosa";
        this.data = data;
        this.errorCode = null;
    }

    // Constructor para errores
    public ApiResponse(String message, String errorCode) {
        this.success = false;
        this.message = message;
        this.data = null;
        this.errorCode = errorCode;
    }

    // Método para crear una respuesta HTTP exitosa
    public static Response success(Object data) {
        return Response.ok(new ApiResponse(data)).build();
    }

    // Método para crear una respuesta de error HTTP
    public static Response error(String message, String errorCode, Response.Status status) {
        return Response.status(status)
                .entity(new ApiResponse(message, errorCode))
                .build();
    }

    // Getters (necesarios para la serialización JSON)
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public String getErrorCode() {
        return errorCode;
    }
}