package people;

import pets.Pet;

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

    public Man(String name, String surname, int year, int iq, Family family) {
        super(name, surname, year, iq, family);
    }

    @Override
    public void greetPet() {
        for (Pet petty : getFamily().getPets()) {
            System.out.printf("Привіт, мій домашній друже, %s\n", petty.getNickname());
        }
    }

    public void repairCar() {
        System.out.println("Ремонтую автомобіль");
    }
}
