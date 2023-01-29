package org.lilia;

import org.lilia.exception.InvalidUserInputException;

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
        System.out.println("6 - additional material");
        System.out.println("7 - exit");

        return Integer.parseInt(readAndValidationInput("[1-7]"));
    }

    public static String readAndValidationInput(String pattern) {
        String stringData = SCANNER.nextLine();
        try {
            validate(stringData, pattern);
        } catch (InvalidUserInputException e) {
            System.out.println(e.getMessage());
            stringData = repeatInputUntilCorrect(pattern);
        }
        return stringData;
    }

    public static void validate(String data, String pattern) {
        boolean b = data.matches(pattern);
        if(!b){
            throw new InvalidUserInputException(data, pattern);
        }
    }

    private static String repeatInputUntilCorrect(String pattern) {
       do {
           String data = SCANNER.nextLine();
           boolean matches = data.matches(pattern);
           if(!matches){
               System.out.println("error, repeat input");
           } else {
               return data;
           }
       } while(true);
    }
}
