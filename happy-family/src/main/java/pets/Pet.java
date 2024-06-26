package pets;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import service.PrettyFormat;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 * description
 *
 * @author Alexander Isai on 19.03.2024.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Dog.class, name = "DOG"),
        @JsonSubTypes.Type(value = DomesticCat.class, name = "DOMESTICCAT"),
        @JsonSubTypes.Type(value = Fish.class, name = "FISH"),
        @JsonSubTypes.Type(value = RoboCat.class, name = "ROBOCAT")
})
public abstract class Pet implements PrettyFormat, Serializable {

    static {
        System.out.println("Загрузился новый класс Pet");
    }

    {
        System.out.println("Новый Pet создан");
    }

    protected Species species = Species.UNKNOWN;
    private String nickname;
    private int age;
    private int trickLevel;
    private Set<String> habits;


    public Pet() {
    }

    public Pet(String nickname) {
        this.nickname = nickname;
    }

    public Pet(String nickname, int age, int trickLevel, Set<String> habits) {
        this.nickname = nickname;
        this.age = age;
        this.trickLevel = trickLevel;
        this.habits = habits;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species == null ? Species.UNKNOWN : species;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public Set<String> getHabits() {
        return habits;
    }

    public void setHabits(Set<String> habits) {
        this.habits = habits;
    }

    public void eat(){
        System.out.println("Я ї'м!");
    }

    public abstract void respond();


    @Override
    public String toString() {
        return prettyFormat();
    }

    @Override
    public String prettyFormat(){
        return "{species=" + species +
                ", nickname='" + nickname +
                ", age=" + age +
                ", trickLevel=" + trickLevel +
                ", habits=" + habits.toString() + "}";
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
        result = 31 * result + habits.hashCode();
        return result;
    }
    @Override
    protected void finalize() {
        System.out.println("Pet удаляется: " + this);
    }

}
