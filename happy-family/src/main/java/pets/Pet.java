package pets;

import java.util.Arrays;
import java.util.Objects;

/**
 * description
 *
 * @author Alexander Isai on 19.03.2024.
 */
public abstract class Pet {

    static {
        System.out.println("Загрузился новый класс pets.Pet");
    }

    {
        System.out.println("Новый pets.Pet создан");
    }

    protected Species species = Species.UNKNOWN;
    private String nickname;
    private int age;
    private int trickLevel;
    private String[] habits;


    public Pet() {
    }

    public Pet(String nickname) {
        this.nickname = nickname;
    }

    public Pet(String nickname, int age, int trickLevel, String[] habits) {
        this.nickname = nickname;
        this.age = age;
        this.trickLevel = trickLevel;
        this.habits = habits;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.species = species == null ? Species.UNKNOWN : species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTrickLevel() {
        return trickLevel;
    }

    public void setTrickLevel(int trickLevel) {
        this.trickLevel = trickLevel;
    }

    public String[] getHabits() {
        return habits;
    }

    public void setHabits(String[] habits) {
        this.habits = habits;
    }

    public void eat(){
        System.out.println("Я ї'м!");
    }

    public abstract void respond();


    @Override
    public String toString() {
        return species + "{nickname='" + nickname +
                ", age=" + age +
                ", trickLevel=" + trickLevel +
                ", habits=" + Arrays.toString(habits) +
                ", canFly=" + species.canFly() +
                ", numberOfLegs=" + species.getNumberOfLegs() +
                ", hasFur=" + species.hasFur() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return age == pet.age && trickLevel == pet.trickLevel && Objects.equals(species, pet.species) && Objects.equals(nickname, pet.nickname);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(species, nickname, age, trickLevel);
        result = 31 * result + Arrays.hashCode(habits);
        return result;
    }
    @Override
    protected void finalize() {
        System.out.println("pets.Pet удаляется: " + this);
    }

}
