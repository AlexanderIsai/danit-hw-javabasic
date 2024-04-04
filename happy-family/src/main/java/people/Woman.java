package people;

import pets.Pet;

/**
 * description
 *
 * @author Alexander Isai on 24.03.2024.
 */
public final class Woman extends Human{

    public Woman() {
        super();
    }

    public Woman(String name, String surname, long birthDay) {
        super(name, surname, birthDay);
    }
    public Woman(String name, String surname, String birthDay, int iq) {
        super(name, surname, birthDay, iq);
    }

    public Woman(String name, String surname, long birthDay, Family family) {
        super(name, surname, birthDay, family);
    }

    public Woman(String name, String surname, long birthDay, int iq, Family family) {
        super(name, surname, birthDay, iq, family);
    }

    @Override
    public void greetPet() {
        for (Pet petty : getFamily().getPets()) {
            System.out.printf("Привіт, моя улюбленниця %s\n", petty.getNickname());
        }
    }

    public void makeup () {
        System.out.println("Я мейкаплюсь");
    }
}
