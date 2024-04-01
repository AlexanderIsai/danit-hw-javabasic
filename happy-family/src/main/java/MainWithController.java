import controller.FamilyController;
import people.Human;
import pets.RoboCat;
import service.CollectionFamilyDao;
import service.FamilyDAO;
import service.FamilyService;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;

/**
 * description
 *
 * @author Alexander Isai on 31.03.2024.
 */
public class MainWithController {

    public static void main(String[] args) {

        Human mother5 = new Human("Mama5", "Mamova5", 2000);
        Human father5 = new Human("Father5", "Fatherov5", 1995);
        mother5.setIq(100);
        father5.setIq(140);
        Human child5 = new Human("Katya", "Katyeva", 2015);
        RoboCat pet4 = new RoboCat("Robby", 1, 5, new HashSet<>(Arrays.asList("charge", "play", "talk")));


        FamilyDAO familyDAO = new CollectionFamilyDao();
        FamilyService familyService = new FamilyService(familyDAO);
        FamilyController familyController = new FamilyController(familyService);

        System.out.println("==== All families");
        familyController.displayAllFamilies();

        System.out.println("==== families are more 3");
        familyController.getFamiliesBiggerThan(3);

        System.out.println("==== families are less 3");
        familyController.getFamiliesLessThan(3);

        System.out.println("==== Count of families with 3 members");
        System.out.println(familyController.countFamiliesWithMemberNumber(3));

        System.out.println("==== create a new family(5)");
        familyController.createNewFamily(mother5, father5);
        familyController.displayAllFamilies();

        System.out.println("==== delete the family (3 with index 2)");
        System.out.println(familyController.deleteFamilyByIndex(2));
        familyController.displayAllFamilies();

        System.out.println("==== born a child (to family5)");
        familyController.bornChild(familyController.getFamilyById(3), "Boy", "Girl");
        familyController.displayAllFamilies();

        System.out.println("==== adopt child (to family5)");
        familyController.adoptChild(familyController.getFamilyById(3), child5);
        familyController.displayAllFamilies();

        System.out.println("==== delete children older 18");
        familyController.deleteAllChildrenOlderThen(18);
        familyController.displayAllFamilies();

        System.out.println("==== Quantity of families");
        System.out.println(familyController.count());

        System.out.println("==== Family`s (4) pets");
        System.out.println(familyController.getPets(2));

        System.out.println("==== add Pet to family(1)");
        familyController.addPet(0, pet4);
        familyController.displayAllFamilies();
    }
}
