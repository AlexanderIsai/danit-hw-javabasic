import service.ConsoleMessage;
import validator.InputValidator;

import java.util.Scanner;

/**
 * description
 *
 * @author Alexander Isai on 07.04.2024.
 */
public class MainConsoleApp {

    static int start = 1;
    static int end = 9;

    static String exit = "exit";

    public static void main(String[] args) {
        AppRunner appRunner = new AppRunner();
        ConsoleMessage consoleMessage = new ConsoleMessage();
        InputValidator inputValidator = new InputValidator();
        Scanner scanner = new Scanner(System.in);
        System.out.println(consoleMessage.getMainMenu());
        String choice = scanner.nextLine();
        choice = inputValidator.checkMainMenuInput(choice, start, end);
        while (!choice.equalsIgnoreCase(exit)){
            appRunner.runMenu(choice);
            System.out.println(consoleMessage.getMainMenu());
            choice = scanner.nextLine();
            choice = inputValidator.checkMainMenuInput(choice, start, end);
        }
    }
}
