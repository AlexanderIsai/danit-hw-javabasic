package controller;

import people.Family;
import people.Human;
import pets.Pet;
import service.FamilyService;

import java.util.List;

/**
 * description
 *
 * @author Alexander Isai on 30.03.2024.
 */
public class FamilyController {

    private FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    public List<Family> getAllFamilies() {
        return familyService.getAllFamilies();
    }

    public void displayAllFamilies() {
        familyService.displayAllFamilies();
    }

    public List<Family> getFamiliesBiggerThan(int quantity) {
        return familyService.getFamiliesBiggerThan(quantity);
    }

    public List<Family> getFamiliesLessThan(int quantity) {
        return familyService.getFamiliesLessThan(quantity);
    }

    public int countFamiliesWithMemberNumber(int quantity) {
        return familyService.countFamiliesWithMemberNumber(quantity);
    }

    public void createNewFamily(Human mother, Human father) {
        familyService.createNewFamily(mother, father);
    }

    public boolean deleteFamilyByIndex(int index) {
        return familyService.deleteFamilyByIndex(index);
    }

    public Family bornChild(Family family, String boysName, String girlsName) {
        return familyService.bornChild(family, boysName, girlsName);
    }

    public Family adoptChild(Family family, Human child) {
        return familyService.adoptChild(family, child);
    }

    public void deleteAllChildrenOlderThen(int age) {
        familyService.deleteAllChildrenOlderThen(age);
    }

    public int count (){
        return familyService.count();
    }

    public Family getFamilyById (int id) {
        return familyService.getFamilyById(id);
    }

    public List<Pet> getPets (int index){
        return familyService.getPets(index);
    }

    public void addPet (int index, Pet pet){
        familyService.addPet(index, pet);
    }
}
