package service;

import people.Family;
import people.Human;
import people.Man;
import people.Woman;
import pets.*;

import java.util.*;

/**
 * description
 *
 * @author Alexander Isai on 29.03.2024.
 */
public class CollectionFamilyDao implements FamilyDAO{

    private List<Family> families;

    public CollectionFamilyDao() {
        this.families = initFamilyList();
    }
    //TODO Временный конструктор с имитацией БД

    private List<Family> initFamilyList() {
        List<Family> familyList = new ArrayList<>();

        Human mother1 = new Human("Mama1", "Mamova1", "21/03/1980", 100);
        Human father1 = new Human("Father1", "Fatherov1", "11/11/1975", 140);
        Woman mother2 = new Woman("Mama2", "Mamova2", "04/04/1985", 80);
        Man father2 = new Man("Father2", "Fatherov2", "10/10/1980", 150);
        Human mother3 = new Woman("Mama3", "Mamova3", "03/03/1990", 100);
        Human father3 = new Man("Father3", "Fatherov3", "07/07/1985", 90);
        Human mother4 = new Human("Mama4", "Mamova4", "06/06/1995", 120);
        Human father4 = new Human("Father4", "Fatherov4", "01/02/1990", 140);

        Human child1 = new Human("Ivan", "Ivanov", "05/06/2000", 140);
        Man child2 = new Man("Petr", "Petrov", "03/11/2002", 160);
        Human child3 = new Man("Alex", "Alexov", "30/08/2010", 140);
        Woman child4 = new Woman("Lena", "Lenina", "08/08/2015", 110);
        Human child5 = new Human("Katya", "Katyeva", "08/12/2005", 110);

        DomesticCat pet1 = new DomesticCat("Kitty", 5, 60, new HashSet<>(Arrays.asList("eat", "sleep", "play")));
        Dog pet2 = new Dog("Doggy", 10, 35, new HashSet<>(Arrays.asList("eat", "bark", "walk")));
        Pet pet3 = new Fish("Fishy", 2, 10, new HashSet<>(Arrays.asList("swim", "eat")));
        RoboCat pet4 = new RoboCat("Robby", 1, 5, new HashSet<>(Arrays.asList("charge", "play", "talk")));
        Pet pet5 = new Dog("Dogger", 3, 55, new HashSet<>(Arrays.asList("sleep", "play", "bark")));

        Family family1 = new Family(mother1, father1);

        Family family2 = new Family(mother2, father2);
        family2.addChild(child1);

        Family family3 = new Family(mother3, father3);
        family3.addChild(child2);
        family3.setPets(pet1);

        Family family4 = new Family(mother4, father4);
        family4.addChild(child3);
        family4.addChild(child4);
        family4.setPets(pet2);
        family4.setPets(pet3);

        familyList.add(family1);
        familyList.add(family2);
        familyList.add(family3);
        familyList.add(family4);



        return familyList;
    }

    @Override
    public List<Family> getAllFamilies() {
        return families;
    }

    @Override
    public Family getFamilyByIndex(int index) {
        return families.get(index);
    }

    @Override
    public boolean deleteFamily(int index) {
        if (index >= 0 && index < families.size()) {
            families.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteFamily(Family family) {
        return families.remove(family);
    }

    @Override
    public void saveFamily(Family family) {
        int index = families.indexOf(family);
        if (index >= 0) {
            families.set(index, family);
        } else {
            families.add(family);
        }
    }

    @Override
    public String toString() {
        return "CollectionFamilyDao{" +
                "families=" + families +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionFamilyDao that = (CollectionFamilyDao) o;
        return Objects.equals(families, that.families);
    }

    @Override
    public int hashCode() {
        return Objects.hash(families);
    }
}
