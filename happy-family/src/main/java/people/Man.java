package people;

/**
 * description
 *
 * @author Alexander Isai on 24.03.2024.
 */
public final class Man extends Human{

    public Man() {
        super();
    }

    public Man(String name, String surname, int year) {
        super(name, surname, year);
    }

    public Man(String name, String surname, int year, Family family) {
        super(name, surname, year, family);
    }

    public Man(String name, String surname, int year, int iq, Family family, String[][] schedule) {
        super(name, surname, year, iq, family, schedule);
    }

    @Override
    public void greetPet() {
        System.out.printf("Привіт, мій домашній друже, %s\n", getFamily().getPet().getNickname());
    }

    public void repairCar() {
        System.out.println("Ремонтую автомобіль");
    }
}
