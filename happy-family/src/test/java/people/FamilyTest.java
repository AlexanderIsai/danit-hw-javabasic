package people;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pets.Dog;
import pets.Pet;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTest {

    private Family family;
    private Human child1;
    private Human child2;

    @BeforeEach
    void setUp() {
        Human mother = new Human("Mutter", "Schmidt", 1965);
        Human father = new Human("Vater", "Schmidt", 1960);
        child1 = new Human("Junge", "Schmidt", 1990);
        child2 = new Human("Madchen", "Schmidt", 1992);
        family = new Family(mother, father);
        family.addChild(child1);
        family.addChild(child2);
    }

    @Test
    void addChild() {
        int initialSize = family.getChildren().size();
        Human alien = new Human("Alien", "Ivanov", 2000);
        family.addChild(alien);

        assertEquals(initialSize + 1, family.getChildren().size());

        assertSame(alien, family.getChildren().get(family.getChildren().size() - 1));

        assertSame(family, alien.getFamily());
    }


    @Test
    void testDeleteExistingChild() {
        assertTrue(family.deleteChild(child1));
        assertEquals(1, family.getChildren().size());
        assertEquals(child2, family.getChildren().get(0));
    }
    @Test
    void testDeleteNotExistingChild() {
        Human alien = new Human("Alien", "Ivanov", 2000);
        assertFalse(family.deleteChild(alien));
        assertEquals(2, family.getChildren().size());
    }

    @Test
    void deleteChildByTrueIndex() {
        assertTrue(family.deleteChild(0));
        assertEquals(1, family.getChildren().size());
        assertEquals(child2, family.getChildren().get(0));
    }

    @Test
    void deleteChildByFalseIndex() {
        assertFalse(family.deleteChild(5));
        assertEquals(2, family.getChildren().size());
    }

    @Test
    void countFamily() {
        assertEquals(4, family.countFamily());
    }

    @Test
    void testToString() {
        Pet pet = new Dog("Rex", 5, 75, new HashSet<>(Arrays.asList("eat", "play")));
        family.setPet(pet);
        System.out.println(family);
        String expected = "Family{mother=Human{name='Mutter', surname='Schmidt', year=1965, iq=0, schedule={}}, " +
                "father=Human{name='Vater', surname='Schmidt', year=1960, iq=0, schedule={}}, " +
                "children=[Human{name='Junge', surname='Schmidt', year=1990, iq=0, schedule={}}, " +
                "Human{name='Madchen', surname='Schmidt', year=1992, iq=0, schedule={}}], " +
                "pet=[DOG{nickname='Rex, age=5, trickLevel=75, habits=[play, eat], canFly=false, numberOfLegs=4, hasFur=true}]}";
        assertEquals(expected, family.toString());
    }

    @Test
    void testEquals() {
        Human alien = new Human("Alien", "Ivanov", 2000);
        Human mother = new Human("Mutter", "Schmidt", 1965);
        Human father = new Human("Vater", "Schmidt", 1960);
        Family family2 = new Family(mother, father);
        family2.addChild(child1);
        family2.addChild(child2);
        assertEquals(family, family2);
        assertNotSame(family, family2);
        family2.addChild(alien);
        assertNotEquals(family, family2);
    }

    @Test
    void testHashCode() {
        Human alien = new Human("Alien", "Ivanov", 2000);
        Human mother = new Human("Mutter", "Schmidt", 1965);
        Human father = new Human("Vater", "Schmidt", 1960);
        Family family2 = new Family(mother, father);
        family2.addChild(child1);
        family2.addChild(child2);
        assertEquals(family.hashCode(), family2.hashCode());
        family2.addChild(alien);
        assertNotEquals(family.hashCode(), family2.hashCode());
    }

    @Test
    void bornChild() {
        int familySize = family.getChildren().size();
        Human child = family.bornChild();

        assertEquals(familySize + 1, family.getChildren().size());
        assertNotNull(child);
        assertTrue(family.getChildren().contains(child));
        assertEquals(child.getFamily(), family);
        assertEquals(child.getSurname(), family.getFather().getSurname());
        assertEquals(child.getIq(), (family.getMother().getIq() + family.getFather().getIq()) / 2);
        assertTrue(child instanceof Man || child instanceof Woman);
    }
}