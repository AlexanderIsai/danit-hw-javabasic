import java.lang.reflect.Field;
import java.util.Arrays;

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


        Pet pet1 = new Pet("cat", "Kitty", 5, 10, new String[]{"eat", "sleep", "play"});
        Pet pet2 = new Pet("dog", "Doggy");
        pet2.setAge(7);
        pet2.setTrickLevel(40);
        pet2.setHabits(new String[]{"eat", "walk", "bark"});
        Pet pet3 = new Pet();
        pet3.setSpecies("fish");
        pet3.setNickname("Fishy");
        pet3.setAge(3);
        pet3.setTrickLevel(10);
        pet3.setHabits(new String[]{"eat", "walk", "bark"});

        Family family1 = new Family(mother, father);
        family1.addChild(child1);
        family1.addChild(child2);
        family1.addChild(child3);
        family1.setPet(pet1);
        System.out.println(family1);
        family1.getPet().foul();
        family1.getPet().eat();
        family1.getPet().respond();
        child1.describePet();
        child1.describePet();


        System.out.println(child1.feedPet(true));
        System.out.println(family1.countFamily());



        printFields(family1);
        System.out.println(family1.deleteChild(0));
        System.out.println(family1.countFamily());
        System.out.println(family1.deleteChild(child3));
        System.out.println(Arrays.toString(family1.getChildren()));
        System.out.println(family1.countFamily());
    }

    public static void printFields(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        System.out.println(clazz.getName() + " fields:");
        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.getName().equals("children")) {
                System.out.println(field.getName() + ": " + field.get(obj));
            } else {
                System.out.println(field.getName() + ": " + Arrays.deepToString((Object[]) field.get(obj)));
            }
        }
    }

}