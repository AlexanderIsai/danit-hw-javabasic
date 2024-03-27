import people.Family;
import people.Human;
import pets.DomesticCat;
import pets.Fish;
import pets.Pet;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {

        Human child1 = new Human("Ivan", "Ivanov", 2000);
        Human child2 = new Human("Petr", "Petrov", 2002);
        Human child3 = new Human("Alex", "Alexov", 2010);
        Human mother = new Human("Mama", "Mamova", 1980);
        Human father = new Human("Father", "Fatherov", 1975);
        child1.setSchedule(child1.initSchedule());
        child2.setSchedule(child1.initSchedule());
        child3.setSchedule(child1.initSchedule());

        mother.setSchedule(child1.initSchedule());
        father.setSchedule(child1.initSchedule());
        mother.setIq(140);
        father.setIq(160);
        DomesticCat pet1 = new DomesticCat("Kitty", 5, 10, new HashSet<>(Arrays.asList("eat", "sleep", "play")));


        Family family1 = new Family(mother, father);
        family1.addChild(child1);
        family1.addChild(child2);
        family1.addChild(child3);
        family1.setPet(pet1);
        System.out.println(family1);
        pet1.foul();
        child1.describePet();

       System.out.println(child1.feedPet(false));
        System.out.println(family1.countFamily());



        printFields(family1);
//        System.out.println(family1.deleteChild(0));
//        System.out.println(family1.countFamily());
//        System.out.println(family1.deleteChild(child3));
//        System.out.println(family1.getChildren());
//        System.out.println(family1.countFamily());
        System.out.println(family1.getPet());
        Fish fish = new Fish("Fishy", 3, 10, new HashSet<>(Arrays.asList("eat", "walk", "bark")));
        System.out.println(fish.getNickname());
        family1.setPet(fish);
        family1.setPet(pet1);
        family1.setPet(fish);
        System.out.println(family1.getPet().size());
        fish.respond();
        family1.bornChild();
        //System.out.println(family1.getChildren()[1].getFamily());
        System.out.println(family1.countFamily());
        System.out.println(family1.getChildren().get(1));

//        for (int i = 0; i < 1000000; i++) {
//            people.Human humanTest = new people.Human("Test", "Testovich" + i, 2000);
//        }

    }

    public static void printFields(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        System.out.println(clazz.getName() + " fields:");
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.getName() + ": " + field.get(obj));
        }
    }

}