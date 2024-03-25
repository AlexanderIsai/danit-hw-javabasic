package people;

/**
 * description
 *
 * @author Alexander Isai on 24.03.2024.
 */
public final class Woman extends Human{

    public Woman() {
        super();
    }

    public Woman(String name, String surname, int year) {
        super(name, surname, year);
    }

    public Woman(String name, String surname, int year, Family family) {
        super(name, surname, year, family);
    }

    public Woman(String name, String surname, int year, int iq, Family family, String[][] schedule) {
        super(name, surname, year, iq, family, schedule);
    }

    @Override
    public void greetPet() {
        System.out.printf("Привіт, моя улюбленниця %s\n", getFamily().getPet().getNickname());
    }

    public void makeup () {
        System.out.println("Я мейкаплюсь");
    }
}
