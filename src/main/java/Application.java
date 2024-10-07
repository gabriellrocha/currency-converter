import exception.InvalidAPIException;
import exception.InvalidOptionException;
import view.ConsoleIO;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {

        try {
            ConsoleIO.start();

        } catch (InvalidAPIException | IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
