package enumeration;

import exception.InvalidOptionException;

public enum Option {

    ZERO("Exit"),
    ONE("Exchange"),
    TWO("Historic");

    private final String describe;

    Option(String describe) {
        this.describe = describe;
    }

    public static void display() {
        for (int i = 0; i < Option.values().length; i++) {
            Option o = Option.values()[i];
            System.out.println(o.ordinal() + " - " + o.describe);
        }

    }

    public static Option fromInt(int value) throws InvalidOptionException {

        for (Option option : Option.values()) {
            if(option.ordinal() == value) {
                return option;
            }
        }
        throw new InvalidOptionException();
    }
}
