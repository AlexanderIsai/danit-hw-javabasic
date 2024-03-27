package people;

import pets.Pet;

import java.util.*;

/**
 * description
 *
 * @author Alexander Isai on 19.03.2024.
 */
public class Human {
    static {
        System.out.println("Загрузился новый класс people.Human");
    }

    {
        System.out.println("Новый people.Human создан");
    }

    private String name;
    private String surname;
    private int year;
    private int iq;
    private Map<DayOfWeek, String> schedule;
    private Family family;

    public Human() {
    }

    public Human(String name, String surname, int year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.schedule = new HashMap<>();
    }

    public Human(String name, String surname, int year, Family family) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.family = family;
    }

    public Human(String name, String surname, int year, int iq, Family family) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.iq = iq;
        this.family = family;
        this.schedule = initSchedule();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public Map<DayOfWeek, String> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<DayOfWeek, String> schedule) {
        this.schedule = schedule;
    }

    public void greetPet(){
        if (family != null && family.getPet() != null) {
            for (Pet petty : family.getPet()) {
                System.out.printf("Привіт, %s\n", petty.getNickname());
            }

        }
    }

    public void describePet(){
        if (family != null && family.getPet() != null) {
            for (Pet petty : family.getPet()) {
                String trick = petty.getTrickLevel() > 50 ? "дуже хитрий" : "майже не хитрий";
                System.out.printf("У мене є %s, їй %d років, він %s\n", petty.getSpecies(), petty.getAge(), trick);
            }
        }
    }

    public boolean feedPet(boolean isTime){
        Random random = new Random();
        boolean isFed = false;
        for (Pet petty : family.getPet()) {
            String petName = petty.getNickname();
            if (isTime || petty.getTrickLevel() > random.nextInt(101)) {
                System.out.printf("Хм... годувати я %s\n", petName);
                isFed = true;
            } else {
                System.out.printf("Думаю, %s не голодний.\n", petName);
            }
        }
        return isFed;
    }

    public Map<DayOfWeek, String> initSchedule(){
        Map<DayOfWeek, String> schedule = new HashMap<>();
        schedule.put(DayOfWeek.SUNDAY, "have a rest, play Volleyball");
        schedule.put(DayOfWeek.MONDAY, "Make java-homework; create working plan");
        schedule.put(DayOfWeek.TUESDAY, "Something read; visit online-lesson");
        schedule.put(DayOfWeek.WEDNESDAY, "Work with the project; job searching");
        schedule.put(DayOfWeek.THURSDAY, "English/German learn; visit online-lesson; shopping");
        schedule.put(DayOfWeek.FRIDAY, "Make java-homework; meet with friends");
        schedule.put(DayOfWeek.SATURDAY, "Visit online-lesson, make homework");
        return schedule;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", year=" + year +
                ", iq=" + iq +
                ", schedule=" + schedule.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return year == human.year && iq == human.iq && Objects.equals(name, human.name) && Objects.equals(surname, human.surname);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, surname, year, iq);
        result = 31 * result + schedule.hashCode();
        return result;
    }
    @Override
    protected void finalize() {
        System.out.println("people.Human удаляется: " + this);
    }
}
