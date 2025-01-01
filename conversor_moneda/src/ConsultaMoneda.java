import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {
    public Moneda conversarMoneda(int opcionMoneda, double cantidad) {
        String baseMoneda = "";
        String targetMoneda = "";


        switch (opcionMoneda) {
            case 1:
                baseMoneda = "USD";
                targetMoneda = "ARS";
                break;
            case 2:
                baseMoneda = "ARS";
                targetMoneda = "USD";
                break;
            case 3:
                baseMoneda = "USD";
                targetMoneda = "BRL";
                break;
            case 4:
                baseMoneda = "BRL";
                targetMoneda = "USD";
                break;
            case 5:
                baseMoneda = "USD";
                targetMoneda = "COP";
                break;
            case 6:
                baseMoneda = "COP";
                targetMoneda = "USD";
                break;
            default:
                throw new RuntimeException("Opción no válida.");
        }


        String urlString = "https://v6.exchangerate-api.com/v6/507b256e4da4cba4a96697b5/pair/" + baseMoneda + "/" + targetMoneda + "/" + cantidad;
        URI direccion = URI.create(urlString);


        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Código de estado: " + response.statusCode());
                System.out.println("Cuerpo de la respuesta: " + response.body());
                throw new RuntimeException("Error: " + response.statusCode() + " - No pude obtener la conversión.");
            }


            return new Gson().fromJson(response.body(), Moneda.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar la moneda: " + e.getMessage());
        }
    }
}
