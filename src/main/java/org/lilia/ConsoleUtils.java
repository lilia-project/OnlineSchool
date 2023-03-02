package org.lilia;

import org.lilia.exception.InvalidUserInputException;
import org.lilia.log.Logger;
import org.lilia.log.LoggerFactory;

import static org.lilia.Main.SCANNER;

public class ConsoleUtils {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleUtils.class);

    public static int readInteger() {
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


    public static int choiceSerialOrDeserialize() {
        ConsoleUtils.print("1 - backup");
        ConsoleUtils.print("2 - deserialization");
        ConsoleUtils.print("3 - exit");

        return Integer.parseInt(readAndValidationInput(Constants.SELECT_ACTION));
    }

    public static int choiceParameterSort() {
        System.out.println("1 - sort by additionMaterialId");
        System.out.println("2 - sort by lectureId");
        System.out.println("3 - sort by resourceType");

        return Integer.parseInt(readAndValidationInput(Constants.SELECT_ACTION));
    }

    private static void feedNewLine() {
        SCANNER.nextLine();
    }

    public static int choiceCategory() {
        System.out.println("select a category:");
        System.out.println("1 - course");
        System.out.println("2 - lecture");
        System.out.println("3 - teacher and student");
        System.out.println("4 - homework");
        System.out.println("5 - additional material");
        System.out.println("6 - control work");
        System.out.println("7 - start server");
        System.out.println("8 - start client");
        System.out.println("9 - backup or deserialization");
        System.out.println("0 - exit");

        return Integer.parseInt(readAndValidationInput("\\d*"));
    }

    public static int choiceCategoryToSerialize() {
        System.out.println("select a category:");
        System.out.println("1 - course");
        System.out.println("2 - lecture");
        System.out.println("3 - teacher");
        System.out.println("4 - student");
        System.out.println("5 - homework");
        System.out.println("6 - additional material");
        System.out.println("0 - exit");

        return Integer.parseInt(readAndValidationInput("[0-6]"));
    }


    public static String readAndValidationInput(String pattern) {
        String stringData = SCANNER.nextLine();
        try {
            validate(stringData, pattern);
        } catch (InvalidUserInputException e) {
            logger.warning("unexpected input", e);
            stringData = repeatInputUntilCorrect(pattern);
        }
        return stringData;
    }

    public static void validate(String data, String pattern) {
        boolean b = data.matches(pattern);
        if (!b) {
            logger.error("invalid user input");
            throw new InvalidUserInputException(data, pattern);
        }
    }

    private static String repeatInputUntilCorrect(String pattern) {
        do {
            String data = SCANNER.nextLine();
            boolean matches = data.matches(pattern);
            if (!matches) {
                logger.error("unexpected input");

            } else {
                return data;
            }
        } while (true);
    }

    public static void print(String string) {
        System.out.println(string);
    }

    public static String choiceParameterResource() {
        System.out.println("1 - URL");
        System.out.println("2 - VIDEO");
        System.out.println("3 - BOOK");

        return readAndValidationInput(Constants.SELECT_ACTION);
    }

    public static int workWithListAddMaterial() {
        System.out.println("\n1 - add additionMaterial to lecture");
        System.out.println("2 - delete additionMaterial");
        System.out.println("3 - sort additionMaterial");
        System.out.println("4 - exit");

        return Integer.parseInt(readAndValidationInput("[1-4]"));
    }

    public static int workWithListHomework() {
        System.out.println("\n1 - add homework to lecture");
        System.out.println("2 - delete homework");
        System.out.println("3 - exit");

        return Integer.parseInt(readAndValidationInput(Constants.SELECT_ACTION));
    }
}