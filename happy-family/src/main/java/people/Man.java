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

    public Man(String name, String surname, long birthDay) {
        super(name, surname, birthDay);
    }

    public Man(String name, String surname, String birthDay, int iq) {
        super(name, surname, birthDay, iq);
    }

    public Man(String name, String surname, long birthDay, Family family) {
        super(name, surname, birthDay, family);
    }

    public Man(String name, String surname, long birthDay, int iq, Family family) {
        super(name, surname, birthDay, iq, family);
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
