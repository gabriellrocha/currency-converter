package enumeration;

import exception.InvalidOptionException;

public enum Currency {

    // UTF-8

    BRL("Brazilian Real", "R$"),
    USD("United States Dollar", "$"),
    EUR("Euro", "€"),
    ARS("Argentine Peso", "$"),
    CLP("Chilean Peso", "$"),
    JPY("Japanese Yen", " ¥");

    private final String country;
    private final String symbol;


    public static void display() {
        System.out.println();
        for (int i=0; i< Currency.values().length; i++) {
            System.out.println(i+1 + " - " + Currency.values()[i] + " " + Currency.values()[i].getCountry());
        }
    }

    Currency(String country, String symbol) {
        this.country = country;
        this.symbol = symbol;
    }

    public String getCountry(){
        return country;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Currency fromInt(int value) throws InvalidOptionException {
        for (Currency currency : Currency.values()) {
            if (currency.ordinal() == value) {
                return currency;
            }
        }
        throw new InvalidOptionException();
    }
}
