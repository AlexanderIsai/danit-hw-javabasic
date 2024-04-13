import controller.FamilyController;
import people.Family;
import people.Human;
import service.*;
import validator.FamilyValidator;
import validator.InputValidator;

import java.util.Scanner;

/**
 * description
 *
 * @author Alexander Isai on 09.04.2024.
 */
public class AppRunner {

    private FamilyDAO familyDAO;
    private FamilyService familyService;
    private FamilyController familyController;
    private ConsoleMessage consoleMessage;
    private Scanner scanner = new Scanner(System.in);
    private InputValidator inputValidator;
    private FamilyValidator familyValidator;
    private FamilyLoader familyLoader;
    private FamilyLoader2 familyLoader2;

    public AppRunner() {
        this.familyDAO = new CollectionFamilyDao();
        this.familyService = new FamilyService(familyDAO);
        this.familyController = new FamilyController(familyService);
        this.consoleMessage = new ConsoleMessage();
        this.inputValidator = new InputValidator();
        this.familyValidator = new FamilyValidator();
        //this.familyLoader = new FamilyLoader();
        this.familyLoader2 = new FamilyLoader2();
    }

    public void runMenu(String choice) {
        String menuChoice;
        switch (choice) {
            case "1":
                familyDAO.initFamilyList();
                break;
            case "2": {
                if (inputValidator.isFamilyListNotEmpty(familyController.getAllFamilies())) {
                    familyController.displayAllFamilies();
                }
            }
            break;
            case "3": {
                if (inputValidator.isFamilyListNotEmpty(familyController.getAllFamilies())) {
                    System.out.println(consoleMessage.getNumberRequest());
                    menuChoice = scanner.nextLine();
                    menuChoice = inputValidator.checkMainMenuInput(menuChoice, 2, 5);
                    familyController.getFamiliesBiggerThan(Integer.parseInt(menuChoice));
                }
            }
            break;
            case "4": {
                if (inputValidator.isFamilyListNotEmpty(familyController.getAllFamilies())) {
                    System.out.println(consoleMessage.getNumberRequest());
                    menuChoice = scanner.nextLine();
                    menuChoice = inputValidator.checkMainMenuInput(menuChoice, 2, 5);
                    familyController.getFamiliesLessThan(Integer.parseInt(menuChoice));
                }
            }
            break;
            case "5": {
                System.out.println(consoleMessage.getNumberRequest());
                menuChoice = scanner.nextLine();
                menuChoice = inputValidator.checkMainMenuInput(menuChoice, 2, 5);
                System.out.println(familyController.countFamiliesWithMemberNumber(Integer.parseInt(menuChoice)));
            }
            break;
            case "6":
                creatingFamily();
                break;
            case "7": {
                if (inputValidator.isFamilyListNotEmpty(familyController.getAllFamilies())) {
                    System.out.println(consoleMessage.getNumberOfFamilyRequest());
                    menuChoice = scanner.nextLine();
                    menuChoice = inputValidator.checkMainMenuInput(menuChoice, 1, familyController.getAllFamilies().size());
                    familyController.deleteFamilyByIndex(Integer.parseInt(menuChoice) - 1);
                }
            }
            break;
            case "8": {
                if (inputValidator.isFamilyListNotEmpty(familyController.getAllFamilies())) {
                    subMenu();
                }
            }
            break;
            case "9": {
                if (inputValidator.isFamilyListNotEmpty(familyController.getAllFamilies())) {
                    System.out.println(consoleMessage.getAgeRequest());
                    menuChoice = scanner.nextLine();
                    menuChoice = inputValidator.checkMainMenuInput(menuChoice, 1, 80);
                    familyController.deleteAllChildrenOlderThen(Integer.parseInt(menuChoice));
                }
            }
            break;
            case "10": familyLoader2.loadData(familyController.getAllFamilies()); break;
            //case "10": familyLoader.loadData(familyController.getAllFamilies()); break;
            case "11": familyController.loadingFamilies(familyLoader2.getDataFromFile()); break;
            //case "11": familyController.loadingFamilies(familyLoader.getDataFromFile()); break;
            default:
                break;
        }
    }

    private void creatingFamily() {
        familyController.createNewFamily(creatingHuman("матері"), creatingHuman("батька"));
    }

    private Human creatingHuman(String type) {
        System.out.println(consoleMessage.newHumanRequest(type)[0]);
        String name = scanner.nextLine();
        System.out.println(consoleMessage.newHumanRequest(type)[1]);
        String surname = scanner.nextLine();
        System.out.println(consoleMessage.newHumanRequest(type)[2]);
        String year = scanner.nextLine();
        year = inputValidator.checkMainMenuInput(year, 1920, 2008);
        System.out.println(consoleMessage.newHumanRequest(type)[3]);
        String month = scanner.nextLine();
        month = inputValidator.checkMainMenuInput(month, 1, 12);
        System.out.println(consoleMessage.newHumanRequest(type)[4]);
        String day = scanner.nextLine();
        day = inputValidator.checkMainMenuInput(day, 1, 31);
        System.out.println(consoleMessage.newHumanRequest(type)[5]);
        String iq = scanner.nextLine();
        iq = inputValidator.checkMainMenuInput(iq, 0, 250);
        Human human = new Human(name, surname, toStringData(year, month, day), Integer.parseInt(iq));
        return human;
    }

    private String toStringData(String year, String month, String day) {
        return String.format("%02d/%02d/%s", Integer.parseInt(day), Integer.parseInt(month), year);
    }

    private void subMenu() {
        System.out.println(consoleMessage.getUpdateFamily());
        String choice = scanner.nextLine();
        choice = inputValidator.checkMainMenuInput(choice, 1, 3);
        switch (choice) {
            case "1":
                borningChild();
                break;
            case "2":
                adoptingChild();
                break;
            case "3":
                break;
        }
    }

    private void borningChild() {
        System.out.println(consoleMessage.getNumberOfFamilyRequest());
        String familyIndex = scanner.nextLine();
        familyIndex = inputValidator.checkMainMenuInput(familyIndex, 1, familyController.getAllFamilies().size());
        Family family = familyController.getFamilyById(Integer.parseInt(familyIndex) - 1);
        familyValidator.checkSizeFamily(family, 4);
        System.out.println(consoleMessage.getChildNameRequest());
        String namesInput = scanner.nextLine();
        namesInput = inputValidator.checkCorrectNames(namesInput);
        String[] names = namesInput.split(" ");
        String boysName = names[0];
        String girlsName = names[1];
        familyController.bornChild(family, boysName, girlsName);
    }

    private void adoptingChild() {
        System.out.println(consoleMessage.getNumberOfFamilyRequest());
        String familyIndex = scanner.nextLine();
        familyIndex = inputValidator.checkMainMenuInput(familyIndex, 1, familyController.getAllFamilies().size());
        Family family = familyController.getFamilyById(Integer.parseInt(familyIndex) - 1);
        familyValidator.checkSizeFamily(family, 4);
        Human child = creatingHuman("дитини");
        familyController.adoptChild(family, child);
    }
}
