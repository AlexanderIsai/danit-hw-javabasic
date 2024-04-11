package validator;

import people.Family;

import java.util.List;
import java.util.Scanner;

/**
 * description
 *
 * @author Alexander Isai on 10.04.2024.
 */
public class InputValidator {

    private Scanner scanner = new Scanner(System.in);

    public InputValidator() {

    }

    public String checkMainMenuInput(String input, int from, int to){
        while (!input.equalsIgnoreCase("exit") && (!isStringToIntAble(input) || !isBetweenFromTo(Integer.parseInt(input), from, to))) {
            System.out.println("Ви ввели некоректні дані. Півторіть спробу");
            input = scanner.nextLine();
        }
        return input;
    }

    public String checkCorrectNames(String names){
        while (names.startsWith(" ") || names.split(" ").length != 2){
            System.out.println("Ви ввели некоректні дані. Півторіть спробу");
            names = scanner.nextLine();
        }
        return names;
    }
    private boolean isStringToIntAble(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean isBetweenFromTo(int target, int from, int to){
        return target >= from && target <= to;
    }

    public boolean isFamilyListNotEmpty(List<Family> list){
        if (list.isEmpty()){
            System.out.println("Жодної сім'ї не створено");
            return false;
        } else {
            return true;
        }
    }
}
