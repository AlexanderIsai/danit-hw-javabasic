package service;

import people.Family;
import people.Human;
import pets.Pet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author Alexander Isai on 29.03.2024.
 */
public class FamilyService {

    private FamilyDAO familyDAO;

    public FamilyService(FamilyDAO familyDAO) {
        this.familyDAO = familyDAO;
    }

    public List<Family> getAllFamilies() {
        return familyDAO.getAllFamilies();
    }

    public void displayAllFamilies() {
        getAllFamilies().forEach(System.out::println);
    }

    public List<Family> getFamiliesBiggerThan(int quantity) {
        List<Family> familyList = getAllFamilies().stream()
                .filter(family -> family != null && family.countFamily() > quantity)
                .toList();
        System.out.println(familyList);
        return familyList;
    }

    public List<Family> getFamiliesLessThan(int quantity) {
        List<Family> familyList = getAllFamilies().stream()
                .filter(family -> family != null && family.countFamily() < quantity)
                .toList();
        System.out.println(familyList);
        return familyList;
    }

    public int countFamiliesWithMemberNumber(int quantity) {
        List<Family> familyList = getAllFamilies().stream()
                .filter(family -> family != null && family.countFamily() == quantity)
                .toList();
        return familyList.size();
    }

    public void createNewFamily(Human mother, Human father) {
        Family newFamily = new Family(mother, father);
        familyDAO.saveFamily(newFamily);
    }

    public boolean deleteFamilyByIndex(int index) {
        return familyDAO.deleteFamily(index);
    }

    public Family bornChild(Family family, String boysName, String girlsName) {
        Human child = family.bornChild();
        if (child.getClass().getSimpleName().equals("Man")) {
            child.setName(boysName);
        } else {
            child.setName(girlsName);
        }
        familyDAO.saveFamily(family);
        return family;
    }

    public Family adoptChild(Family family, Human child) {
        family.addChild(child);
        familyDAO.saveFamily(family);
        return family;
    }

    public void deleteAllChildrenOlderThen(int age) {
        getAllFamilies().forEach(family -> {
            if (family != null && family.countFamily() > 2) {
                List<Human> toDelete = family.getChildren().stream()
                        .filter(child -> child.getYear() > age)
                        .toList();
                toDelete.forEach(child -> {
                    family.deleteChild(child);
                    familyDAO.saveFamily(family);
                });
            }
        });
    }

    public int count (){
        return getAllFamilies().size();
    }

    public Family getFamilyById (int id) {
        return familyDAO.getFamilyByIndex(id);
    }

    public List<Pet> getPets (int index){
        Family family = getFamilyById(index);
        return new ArrayList<>(family.getPets());
    }

    public void addPet (int index, Pet pet){
        Family family = getFamilyById(index);
        family.setPets(pet);
        familyDAO.saveFamily(family);
    }
}
