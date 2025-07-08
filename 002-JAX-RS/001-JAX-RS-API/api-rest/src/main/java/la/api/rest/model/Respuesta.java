package la.api.rest.model;

public class Respuesta {
    private String saludo;

    // Constructor
    public Respuesta(String saludo) {
        this.saludo = saludo;
    }

    // Getter (necesario para la serialización JSON)
    public String getSaludo() {
        return saludo;
    }
}