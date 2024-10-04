package service;

import com.google.gson.Gson;
import dto.Exchange;
import enumeration.Currency;
import exception.InvalidOptionException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientService {

    private static HttpClient client;

    public HttpClientService() {

        client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .build();
    }

    public Exchange sendGetRequest(Currency baseCurrency, Currency targetCurrency, String amount) throws IOException, InterruptedException, InvalidOptionException {

        URI uri = URI.create("https://v6.exchangerate-api.com/v6/8367040237e31577f413e8cf/pair/" +
                baseCurrency +
                "/" +
                targetCurrency +
                "/" +
                amount
        );

        HttpRequest request = HttpRequest.newBuilder()
                .timeout(Duration.ofSeconds(3))
                .GET()
                .uri(uri)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200) {
            throw new InvalidOptionException("Erro. Status Code " + response.statusCode());
        }

        return new Gson().fromJson(response.body(), Exchange.class);

    }
}
