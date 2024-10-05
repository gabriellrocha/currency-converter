package exception;

import service.HttpClientService;

import java.net.http.HttpResponse;

public class HttpClientErrorException extends Exception {

    public HttpClientErrorException (HttpResponse<String> response) {
        super("Erro. Status code " + response.statusCode());
    }
}
