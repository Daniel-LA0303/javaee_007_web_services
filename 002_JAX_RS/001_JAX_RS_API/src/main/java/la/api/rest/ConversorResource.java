package la.api.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import la.api.rest.model.ApiResponse;
import la.api.rest.model.Respuesta;

@Path("/conversor")
public class ConversorResource {
    
    private final ConversorService service = new ConversorService();
    
    @GET
    @Path("/hola")
    @Produces(MediaType.APPLICATION_JSON)
    public Response decirHola() {
        Respuesta respuesta = new Respuesta("Hola");
        return Response.ok(respuesta).build();
    }
    
    //http://localhost:8080/api-rest-0.0.1-SNAPSHOT/api/conversor/decimal-a-binario?numero=42
    @GET
    @Path("/decimal-a-binario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertirDecimalABinario(@QueryParam("numero") int numero) {
        try {
            // Validación básica
            if (numero < 0) {
                return ApiResponse.error(
                    "El número debe ser positivo", 
                    "CONV_001", 
                    Response.Status.BAD_REQUEST
                );
            }
            
            String binario = service.decimalABinario(numero);
            return ApiResponse.success(binario);
            
        } catch (Exception e) {
            return ApiResponse.error(
                "Error en la conversión: " + e.getMessage(),
                "CONV_500",
                Response.Status.INTERNAL_SERVER_ERROR
            );
        }
    }

    // http://localhost:8080/api-rest/api/conversor/binario-a-decimal?binario=101010
    @GET
    @Path("/binario-a-decimal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertirBinarioADecimal(@QueryParam("binario") String binario) {
        try {
            // Validar que sea binario (solo 0 y 1)
            if (binario == null || !binario.matches("[01]+")) {
                return ApiResponse.error(
                    "Cadena binaria no válida. Solo se permiten 0 y 1",
                    "CONV_002",
                    Response.Status.BAD_REQUEST
                );
            }
            
            int decimal = service.binarioADecimal(binario);
            return ApiResponse.success(decimal);
            
        } catch (NumberFormatException e) {
            return ApiResponse.error(
                "Formato binario inválido: " + e.getMessage(),
                "CONV_003",
                Response.Status.BAD_REQUEST
            );
        }
    }
}