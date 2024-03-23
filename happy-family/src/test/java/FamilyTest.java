import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        int initialSize = family.getChildren().length;
        Human alien = new Human("Alien", "Ivanov", 2000);
        family.addChild(alien);

        assertEquals(initialSize + 1, family.getChildren().length);

        assertSame(alien, family.getChildren()[family.getChildren().length - 1]);

        assertSame(family, alien.getFamily());
    }


    @Test
    void testDeleteExistingChild() {
        assertTrue(family.deleteChild(child1));
        assertEquals(1, family.getChildren().length);
        assertEquals(child2, family.getChildren()[0]);
    }
    @Test
    void testDeleteNotExistingChild() {
        Human alien = new Human("Alien", "Ivanov", 2000);
        assertFalse(family.deleteChild(alien));
        assertEquals(2, family.getChildren().length);
    }

    @Test
    void deleteChildByTrueIndex() {
        assertTrue(family.deleteChild(0));
        assertEquals(1, family.getChildren().length);
        assertEquals(child2, family.getChildren()[0]);
    }

    @Test
    void deleteChildByFalseIndex() {
        assertFalse(family.deleteChild(5));
        assertEquals(2, family.getChildren().length);
    }

    @Test
    void countFamily() {
        assertEquals(4, family.countFamily());
    }

    @Test
    void testToString() {
        Pet pet = new Pet(Species.DOG, "Rex", 5, 75, new String[]{"eat", "play"});
        family.setPet(pet);

        String expected = "Family{mother=Human{name='Mutter', surname='Schmidt', year=1965, iq=0, schedule=null}, " +
                "father=Human{name='Vater', surname='Schmidt', year=1960, iq=0, schedule=null}, " +
                "children=[Human{name='Junge', surname='Schmidt', year=1990, iq=0, schedule=null}, " +
                "Human{name='Madchen', surname='Schmidt', year=1992, iq=0, schedule=null}], " +
                "pet=DOG{nickname='Rex, age=5, trickLevel=75, habits=[eat, play], canFly=false, numberOfLegs=4, hasFur=true}}";
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
}