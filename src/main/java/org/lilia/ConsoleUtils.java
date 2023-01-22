package org.lilia;

import static org.lilia.Main.SCANNER;

public class ConsoleUtils {
    public static int readInteger() { //scanner processing for integer
        int id = SCANNER.nextInt();
        feedNewLine();
        return id;
    }

    public static int choiceAction() {
        System.out.println("1 - create new");
        System.out.println("2 - open/edit");
        System.out.println("3 - output all");
        System.out.println("4 - delete");
        System.out.println("5 - exit");

        return Integer.parseInt(readAndValidationInput("[1-5]"));
    }

    private static void feedNewLine() { //scanner processing for button enter
        SCANNER.nextLine();
    }

    public static int choiceCategory() {
        System.out.println("select a category:");
        System.out.println("1 - course");
        System.out.println("2 - lecture");
        System.out.println("3 - teacher");
        System.out.println("4 - student");
        System.out.println("5 - homework");
        System.out.println("6 - exit");

        return Integer.parseInt(readAndValidationInput("[1-6]"));
    }

    public static String readAndValidationInput(String pattern) {
        String stringData = SCANNER.nextLine();
        stringData = validate(stringData, pattern);
        return stringData;
    }

    public static String validate(String data, String pattern) {
        boolean b = data.matches(pattern);
        while (!b) {
            System.out.println("error, repeat input");
            data = SCANNER.nextLine();
            b = data.matches(pattern);
        }
        return data;
    }
}
