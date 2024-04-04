package people;

import pets.Pet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * description
 *
 * @author Alexander Isai on 20.03.2024.
 */
public class Family implements HumanCreator{

    static {
        System.out.println("Загрузился новый класс Family");
    }

    {
        System.out.println("Новая Family создана");
    }

    private Human mother;
    private Human father;
    private List<Human> children;
    private Set<Pet> pets;

    public Family(Human mother, Human father) {
        this.mother = mother;
        this.father = father;
        this.children = new ArrayList<>();
        this.pets = new HashSet<>();
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

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Pet pet) {
        if (this.pets == null) {
            this.pets = new HashSet<>();
        }
        this.pets.add(pet);
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
                ", pets=" + pets +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(mother, family.mother) && Objects.equals(father, family.father) && children.equals(family.children) && Objects.equals(pets, family.pets);
    }
    @Override
    public int hashCode() {
        int result = Objects.hash(mother, father, pets);
        result = 31 * result + children.hashCode();
        return result;
    }
    @Override
    protected void finalize() {
        System.out.println("Family удаляется: " + this);
    }

    @Override
    public Human bornChild() {
        Random random = new Random();
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = localDate.format(formatter);
        String[] boysNames = new String[]{"Ivan", "Petr", "Pablo", "Alex", "Olaf"};
        String[] girlsNames = new String[]{"Maria", "Anna", "Helen", "Olga", "Kate"};
        int iq = (father.getIq() + mother.getIq()) / 2;
        Human child = random.nextBoolean() ? new Man(boysNames[random.nextInt(boysNames.length)], father.getSurname(), formattedDate, iq) : new Woman(girlsNames[random.nextInt(girlsNames.length)], father.getSurname(), formattedDate, iq);
        this.addChild(child);
        child.setFamily(this);
        return child;
    }
}
