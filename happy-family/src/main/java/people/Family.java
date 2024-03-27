package people;

import people.Human;
import pets.Pet;

import java.util.*;

/**
 * description
 *
 * @author Alexander Isai on 20.03.2024.
 */
public class Family implements HumanCreator{

    static {
        System.out.println("Загрузился новый класс people.Family");
    }

    {
        System.out.println("Новая people.Family создана");
    }

    private Human mother;
    private Human father;
    private List<Human> children;
    private Set<Pet> pet;

    public Family(Human mother, Human father) {
        this.mother = mother;
        this.father = father;
        this.children = new ArrayList<>();
        this.mother.setFamily(this);
        this.father.setFamily(this);
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public List<Human> getChildren() {
        return children;
    }

    public void setChildren(List<Human> children) {
        this.children = children;
    }

    public Set<Pet> getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        if (this.pet == null) {
            this.pet = new HashSet<>();
        }
        this.pet.add(pet);
    }

    public void addChild(Human child){
        children.add(child);
        child.setFamily(this);
    }

    public boolean deleteChild(int index){
        if (index < 0 || index >= children.size()){
            return false;
        }
        Human child = children.get(index);
        child.setFamily(null);
        children.remove(index);

        return true;
    }

    public boolean deleteChild(Human child){
        boolean result = children.remove(child);
        if (result) {
            child.setFamily(null);
        }
        return result;
    }


    public int countFamily(){
        return children.size() + 2;
    }

    @Override
    public String toString() {
        return "Family{" +
                "mother=" + mother +
                ", father=" + father +
                ", children=" + children.toString() +
                ", pet=" + pet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(mother, family.mother) && Objects.equals(father, family.father) && children.equals(family.children) && Objects.equals(pet, family.pet);
    }
    @Override
    public int hashCode() {
        int result = Objects.hash(mother, father, pet);
        result = 31 * result + children.hashCode();
        return result;
    }
    @Override
    protected void finalize() {
        System.out.println("people.Family удаляется: " + this);
    }

    @Override
    public Human bornChild() {
        Random random = new Random();
        String[] boysNames = new String[]{"Ivan", "Petr", "Pablo", "Alex", "Olaf"};
        String[] girlsNames = new String[]{"Maria", "Anna", "Helen", "Olga", "Kate"};
        Human child = random.nextBoolean() ? new Man(boysNames[random.nextInt(boysNames.length)], father.getSurname(), 2024) : new Woman(girlsNames[random.nextInt(girlsNames.length)], father.getSurname(), 2024);
        child.setIq((father.getIq() + mother.getIq()) / 2);
        this.addChild(child);
        child.setFamily(this);
        return child;
    }
}
