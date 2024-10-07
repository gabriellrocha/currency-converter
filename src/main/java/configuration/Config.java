package configuration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import exception.InvalidAPIException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Config {

    private static String key = null;

    public static void isValidKey() throws InvalidAPIException, IOException, InterruptedException {

        Config.key = System.getenv("API_KEY");
        if(Config.key == null) {
            throw new InvalidAPIException();
        }

        String jsonResponse = sendRequest(Config.key);
        JsonElement[] elements = parseJson(jsonResponse);

        System.out.println("\nAPI KEY OK. Plan quota = " + elements[0] + " Requests remaining = " + elements[1]);
    }

    private static String sendRequest(String key) throws IOException, InterruptedException, InvalidAPIException {

        HttpClient client = HttpClient.newBuilder().build();

        URI uri = URI.create("https://v6.exchangerate-api.com/v6/" +
                        key +
                        "/quota");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new InvalidAPIException();
        }
        return response.body();
    }

    private static JsonElement[] parseJson(String json) {

        JsonElement[] jsonElements = new JsonElement[2];

        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        jsonElements[0] = jsonObject.get("plan_quota");
        jsonElements[1] = jsonObject.get("requests_remaining");

        return jsonElements;
    }

    public static String getKey() {
        return Config.key;
    }
}
