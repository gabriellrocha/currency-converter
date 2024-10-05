package service;

import com.google.gson.Gson;
import model.dto.ExchangeDTO;
import enumeration.Currency;
import exception.HttpClientErrorException;
import exception.InvalidOptionException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientService {

    private static HttpClient client;
    private static final Gson gson = new Gson();

    public HttpClientService() {

        client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .build();
    }

    public ExchangeDTO sendGetRequest(Currency baseCurrency, Currency targetCurrency, String amount) throws IOException, InterruptedException, InvalidOptionException, HttpClientErrorException {

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
            throw new HttpClientErrorException(response);
        }

        return gson.fromJson(response.body(), ExchangeDTO.class);

    }
}
