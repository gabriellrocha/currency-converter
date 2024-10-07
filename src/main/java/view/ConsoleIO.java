package view;

import configuration.Config;
import exception.InvalidAPIException;
import model.Exchange;
import model.dto.ExchangeDTO;
import enumeration.Currency;
import enumeration.Option;
import exception.HttpClientErrorException;
import exception.InvalidOptionException;
import service.HttpClientService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ConsoleIO {

    private static boolean running = true;
    private static final HttpClientService service = new HttpClientService();
    private static final List<Exchange> historic = new ArrayList<>();

    public static void start() throws IOException, InterruptedException, InvalidAPIException {

        Config.isValidKey();

        System.out.println("\n\tWelcome to the currency converter\n" + "*".repeat(50));


        while (ConsoleIO.running) {

            try {
                Option.display();
                System.out.println("\nEnter an option: ");
                Option option = Option.fromInt(readInt());

                switch (option) {

                    case ZERO -> {
                        toggleRunning();
                    }

                    case ONE -> {
                        Currency.display();
                        System.out.println("\nEnter the number that corresponds to the base currency: ");
                        Currency currencyBase = Currency.fromInt(readInt() - 1);

                        System.out.println("Enter the number for the currency you want to convert to: ");
                        Currency currencyTarget = Currency.fromInt(readInt() - 1);
                        String amount = getAmount();

                        ExchangeDTO dto = service.sendGetRequest(currencyBase, currencyTarget, amount);
                        historic.add(new Exchange(dto));
                        System.out.println(historic.get(historic.size()-1));
                    }

                    case TWO -> displayHistoric();
                }


            } catch (InvalidOptionException | HttpClientErrorException e) {
                System.err.println(e.getMessage());

            } catch (IOException | InterruptedException e) {
                System.out.println("Error contact support");
                toggleRunning();
            }
        }

        System.out.println("Thank you for using this application!");
    }


    private static int readInt() throws IOException, InvalidOptionException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
           return Integer.parseInt(bufferedReader.readLine().trim());

        } catch (NumberFormatException e) {
            throw new InvalidOptionException();
        }
    }

    private static String getAmount() throws IOException {
        System.out.println("Enter the amount: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String amount = bufferedReader.readLine();
        if (amount.contains(",")) {
            amount = amount.replace(",", ".");
        }

        return amount;
    }

    private static void displayHistoric() {
        if (historic.isEmpty()) {
            System.out.println("No registration\n");
        }

        historic.forEach(System.out::println);
    }

    private static void toggleRunning() {
        ConsoleIO.running = !running;
    }

}
