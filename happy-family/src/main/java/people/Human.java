package people;

import pets.Pet;
import service.PrettyFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * description
 *
 * @author Alexander Isai on 19.03.2024.
 */
public class Human implements PrettyFormat {
    static {
        System.out.println("Загрузился новый класс Human");
    }

    {
        System.out.println("Новый Human создан");
    }

    private String name;
    private String surname;
    private long birthDate;
    private int iq;
    private Map<DayOfWeek, String> schedule;
    private Family family;


    public Human() {
    }

    public Human(String name, String surname, long birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.schedule = new HashMap<>();
    }

    public Human(String name, String surname, long birthDate, Family family) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.family = family;
    }

    public Human(String name, String surname, long birthDate, int iq, Family family) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.iq = iq;
        this.family = family;
        this.schedule = initSchedule();
    }

    public Human(String name, String surname, String birthDate, int iq) {
        this.name = name;
        this.surname = surname;
        this.iq = iq;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDateLocal = LocalDate.parse(birthDate, formatter);
        this.birthDate = birthDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.schedule = new HashMap<>();
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

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
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
        if (family != null && family.getPets() != null) {
            for (Pet petty : family.getPets()) {
                System.out.printf("Привіт, %s\n", petty.getNickname());
            }
        }
    }

    public void describePet(){
        if (family != null && family.getPets() != null) {
            for (Pet petty : family.getPets()) {
                String trick = petty.getTrickLevel() > 50 ? "дуже хитрий" : "майже не хитрий";
                System.out.printf("У мене є %s, їй %d років, він %s\n", petty.getSpecies(), petty.getAge(), trick);
            }
        }
    }

    public boolean feedPet(boolean isTime){
        Random random = new Random();
        boolean isFed = false;
        for (Pet petty : family.getPets()) {
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

    public String describeAge(){
        LocalDate birthDateLocal = LocalDate.ofInstant(Instant.ofEpochMilli(birthDate), ZoneId.systemDefault());
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDateLocal, currentDate);
        return String.format("%d years, %d months, and %d days", period.getYears(), period.getMonths(), period.getDays());
    }

    public int getYear(){
        String date = this.describeAge();
        return Integer.parseInt(date.split(" ")[0]);
    }


    @Override
    public String toString() {
        return prettyFormat();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return birthDate == human.birthDate && iq == human.iq && Objects.equals(name, human.name) && Objects.equals(surname, human.surname);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, surname, birthDate, iq);
        result = 31 * result + schedule.hashCode();
        return result;
    }
    @Override
    protected void finalize() {
        System.out.println("Human удаляется: " + this);
    }

    @Override
    public String prettyFormat() {
        LocalDate birthDateLocal = Instant.ofEpochMilli(birthDate).atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String birthDateString = birthDateLocal.format(formatter);
        String humanType = getHumanType();
        return          humanType +
                        "{name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", birthDate=" + birthDateString +
                        ", iq=" + iq +
                        ", schedule=" + schedule.toString() +
                        '}';
    }

    private String getHumanType(){
        return switch (this.getClass().getSimpleName()) {
            case "Man" -> "boy: ";
            case "Woman" -> "girl: ";
            default -> "";
        };
    }
}
