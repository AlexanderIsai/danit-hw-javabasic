import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * description
 *
 * @author Alexander Isai on 19.03.2024.
 */
public class Human {
    static {
        System.out.println("Загрузился новый класс Human");
    }

    {
        System.out.println("Новый Human создан");
    }

    private String name;
    private String surname;
    private int year;
    private int iq;
    private String[][] schedule;
    private Family family;

    public Human() {
    }

    public Human(String name, String surname, int year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    public Human(String name, String surname, int year, Family family) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.family = family;
    }

    public Human(String name, String surname, int year, int iq, Family family, String[][] schedule) {
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

    public String[][] getSchedule() {
        return schedule;
    }

    public void setSchedule(String[][] schedule) {
        this.schedule = schedule;
    }

    public void greetPet(){
        if (family != null && family.getPet() != null) {
            System.out.printf("Привіт, %s\n", family.getPet().getNickname());
        }
    }

    public void describePet(){
        if (family != null && family.getPet() != null) {
            String trick = family.getPet().getTrickLevel() > 50 ? "дуже хитрий" : "майже не хитрий";
            System.out.printf("У мене є %s, їй %d років, він %s\n", family.getPet().getSpecies(), family.getPet().getAge(), trick);
        }
    }

    public boolean feedPet(boolean isTime){
        Random random = new Random();
        String petName = family.getPet().getNickname();
        if(isTime || family.getPet().getTrickLevel() > random.nextInt(101)){
            System.out.printf("Хм... годувати я %s\n", petName);
            return true;
        }
        else {
            System.out.printf("Думаю, %s не голодний.\n", petName);
            return false;
        }
    }

    public String[][] initSchedule(){
        String[][] schedule = new String[7][2];
        schedule[0][0] = DayOfWeek.SUNDAY.name();
        schedule[0][1] = "have a rest, play Volleyball";
        schedule[1][0] = DayOfWeek.MONDAY.name();
        schedule[1][1] = "Make java-homework; create working plan";
        schedule[2][0] = DayOfWeek.TUESDAY.name();
        schedule[2][1] = "Something read; visit online-lesson";
        schedule[3][0] = DayOfWeek.WEDNESDAY.name();
        schedule[3][1] = "Work with the project; job searching";
        schedule[4][0] = DayOfWeek.THURSDAY.name();
        schedule[4][1] = "English/German learn; visit online-lesson; shopping";
        schedule[5][0] = DayOfWeek.FRIDAY.name();
        schedule[5][1] = "Make java-homework; meet with friends";
        schedule[6][0] = DayOfWeek.SATURDAY.name();
        schedule[6][1] = "Visit online-lesson, make homework";
        return schedule;
    }


    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", year=" + year +
                ", iq=" + iq +
                ", schedule=" + Arrays.deepToString(schedule) +
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
        result = 31 * result + Arrays.deepHashCode(schedule);
        return result;
    }
    @Override
    protected void finalize() {
        System.out.println("Human удаляется: " + this);
    }
}
