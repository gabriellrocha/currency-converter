package view;

import dto.Exchange;
import enumeration.Currency;
import exception.InvalidOptionException;
import service.HttpClientService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ConsoleIO {

    private static final HttpClientService service = new HttpClientService();

    public static void start() {
        boolean run = true;

        printWelcome();
        Currency.showCurrency();

        do {
            try {
                System.out.println("\nEnter the base currency for the exchange (0 to exit) ");
                int option = optionNumber();

                if(option == 0) {
                    break;
                }

                Currency currencyBase = getCurrency(option -1);

                System.out.println("Enter the target currency: ");
                option = optionNumber();
                Currency currencyTarget = getCurrency(option -1 );


                System.out.println("Enter the amount: ");
                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
                String amount = bf.readLine().trim();
                if (amount.contains(",")){
                    amount = amount.replace(",", ".");
                }

                Exchange exchange = service.sendGetRequest(currencyBase, currencyTarget, amount);
                System.out.println(exchange);

            } catch (InvalidOptionException e) {
                System.err.println(e.getMessage());

            } catch (IOException | InterruptedException e) {
                System.out.println("Error contact support");
                run = false;
            }

        } while (run);

        System.out.println("Thank you for using this application!");
    }


    private static void printWelcome() {
        String welcome = """
                \t\tWelcome to the currency converter
                """;

        System.out.print(welcome);
        System.out.println("_".repeat(55));
    }

    private static Currency getCurrency(int number) throws InvalidOptionException {

        try {
            return Currency.values()[number];

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidOptionException("Invalid Option. Please, choose one of the available options");
        }
    }

    private static int optionNumber() throws IOException, InvalidOptionException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return Integer.parseInt(bufferedReader.readLine().trim());

        } catch (NumberFormatException e) {
            throw new InvalidOptionException("Only valid numbers");
        }
    }
}
