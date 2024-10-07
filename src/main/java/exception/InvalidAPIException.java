package exception;

public class InvalidAPIException extends Exception {

    public InvalidAPIException() {
        super("Invalid API key. Get free in 'https://www.exchangerate-api.com/'");
    }
}
