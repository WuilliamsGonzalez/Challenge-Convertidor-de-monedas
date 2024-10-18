import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsultaMonedas {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public Monedas buscarMoneda(String divisaBase) throws IOException {
        String urlStr = API_URL + divisaBase;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new IOException("Error al obtener datos de la API: " + conn.getResponseCode());
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return Monedas.fromJson(response.toString());
        }
    }
}
