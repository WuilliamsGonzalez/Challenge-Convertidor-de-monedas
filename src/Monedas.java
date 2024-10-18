import com.google.gson.Gson;
import java.util.Map;

public class Monedas {
    private String base;
    private Map<String, Double> rates;

    public Double obtenerTasaConversion(String divisaDestino) {
        Double tasa = rates.get(divisaDestino);
        if (tasa == null) {
            throw new IllegalArgumentException("Moneda o divisa de destino no válida.");
        }
        return tasa;
    }

    public static Monedas fromJson(String jsonResponse) {
        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, Monedas.class);
    }
}
