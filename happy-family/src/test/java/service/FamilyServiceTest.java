package service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import people.Family;
import people.Human;
import people.Man;
import pets.Dog;
import pets.Pet;
import pets.RoboCat;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FamilyServiceTest {
    @Mock
    private FamilyDAO familyDAO;

    @InjectMocks
    private FamilyService familyService;

    private final List<Family> familyList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Family family1 = new Family(new Human("Mama1", "Mamova1", "01/05/1980", 100), new Human("Papa1", "Papov1", "01/02/1979", 120));
        Family family2 = new Family(new Human("Mama2", "Mamova2", "02/05/1985", 110), new Human("Papa2", "Papov2", "02/02/1984", 130));
        Human child5 = new Human("Katya", "Katyeva", "01/03/2015", 110);
        family1.addChild(child5);

        familyList.add(family1);
        familyList.add(family2);

        when(familyDAO.getAllFamilies()).thenReturn(familyList);
    }

    @Test
    void getAllFamilies() {
        List<Family> families = familyService.getAllFamilies();
        assertEquals(familyList.size(), families.size());
        assertEquals(familyList, families);
    }
    @Test
    void getFamiliesBiggerThan() {
        int quantity = 2;
        List<Family> familiesBiggerThan = familyService.getFamiliesBiggerThan(quantity);
        assertEquals(1, familiesBiggerThan.size());
        assertEquals("Mama1", familiesBiggerThan.get(0).getMother().getName());
    }

    @Test
    void getFamiliesLessThan() {
        int quantity = 3;
        List<Family> familiesLessThan = familyService.getFamiliesLessThan(quantity);
        assertEquals(1, familiesLessThan.size());
        assertEquals("Mama2", familiesLessThan.get(0).getMother().getName());
    }

    @Test
    void countFamiliesWithMemberNumber() {
        int quantity = 3;
        int countFamilies = familyService.countFamiliesWithMemberNumber(quantity);
        assertEquals(1, countFamilies);
        assertEquals("Mama1", familyList.get(0).getMother().getName());
    }

    @Test
    void createNewFamily() {
        Human mother1 = new Human("Mutter1", "Mutterova", "03/05/1992", 120);
        Human father1 = new Human("Vater1", "Vaterov", "03/02/1993", 160);
        familyService.createNewFamily(mother1, father1);
        verify(familyDAO, times(1)).saveFamily(any(Family.class));
    }

    @Test
    void deleteFamilyByIndex() {
        int index = 0;
        familyService.deleteFamilyByIndex(index);
        verify(familyDAO, times(1)).deleteFamily(index);
    }

    @Test
    void bornChild() {
        Family family = mock(Family.class);
        when(family.bornChild()).thenReturn(new Man("Name", "Surname", "02/04/2024", 120));
        when(family.getChildren()).thenReturn(List.of(new Human("Boy", "Surname", "02/04/2024", 120)));
        String boysName = "Boy";
        String girlsName = "Girl";
        familyService.bornChild(family, boysName, girlsName);
        verify(family, times(1)).bornChild();
        verify(familyDAO, times(1)).saveFamily(family);
        Human lastChild = family.getChildren().get(family.getChildren().size() - 1);
        assertEquals(boysName, lastChild.getName());
    }

    @Test
    void adoptChild() {
        Family family = mock(Family.class);
        Human child = mock(Human.class);
        familyService.adoptChild(family, child);
        verify(family, times(1)).addChild(child);
        verify(familyDAO, times(1)).saveFamily(family);
    }
    @Test
    void deleteAllChildrenOlderThen() {
        Family family = new Family(new Human("Mom", "MomSurname", "05/05/1970", 120),
                new Human("Dad", "DadSurname", "05/02/1965", 150));
        Human childOlder = new Human("Old", "Surname", "02/09/2004", 135);
        Human childYounger = new Human("Young", "Surname", "03/09/2015", 135);
        family.addChild(childOlder);
        family.addChild(childYounger);
        when(familyDAO.getAllFamilies()).thenReturn(List.of(family));
        familyService.deleteAllChildrenOlderThen(18);
        assertEquals(1, family.getChildren().size());
        assertEquals(childYounger, family.getChildren().get(0));
        verify(familyDAO, times(1)).saveFamily(family);
    }

    @Test
    void count() {
        int count = familyService.count();
        assertEquals(2, count);
    }

    @Test
    void getFamilyById() {
        int id = 0;
        Family expectedFamily = familyList.get(id);

        when(familyDAO.getFamilyByIndex(id)).thenReturn(expectedFamily);

        Family actualFamily = familyService.getFamilyById(id);
        assertEquals(expectedFamily, actualFamily);
        verify(familyDAO, times(1)).getFamilyByIndex(id);
    }

    @Test
    void getPets() {
        int familyIndex = 0;
        Family family = new Family(new Human("Mother", "Surname", "06/05/1970", 120), new Human("Father", "Surname", "06/02/1965", 140));
        family.setPets(new RoboCat("Robby", 1, 5, new HashSet<>(Arrays.asList("charge", "play", "talk"))));
        family.setPets(new Dog("Doggy", 10, 55, new HashSet<>(Arrays.asList("bark", "play", "walk"))));
        Set<Pet> expectedPets = family.getPets();

        when(familyDAO.getFamilyByIndex(familyIndex)).thenReturn(family);
        List<Pet> actualPets = familyService.getPets(familyIndex);

        assertNotNull(actualPets);
        assertEquals(expectedPets.size(), actualPets.size());
        assertTrue(actualPets.containsAll(expectedPets));
        verify(familyDAO, times(1)).getFamilyByIndex(familyIndex);
    }

    @Test
    void addPet() {
        int index = 0;
        Family expectedFamily = familyList.get(index);
        Pet newPet = new Dog("Doggo", 5, 75, new HashSet<>(Arrays.asList("bark", "play")));
        when(familyDAO.getFamilyByIndex(index)).thenReturn(expectedFamily);
        familyService.addPet(index, newPet);
        assertTrue(expectedFamily.getPets().contains(newPet));
        verify(familyDAO).saveFamily(expectedFamily);
    }
}