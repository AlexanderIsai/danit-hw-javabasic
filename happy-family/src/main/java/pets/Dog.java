package pets;

/**
 * description
 *
 * @author Alexander Isai on 24.03.2024.
 */
public class Dog extends Pet implements Foul{

    public Dog(String nickname, int age, int trickLevel, String[] habits) {
        super(nickname, age, trickLevel, habits);
        this.species = Species.DOG;
    }
    @Override
    public void respond() {
        System.out.printf("Привіт, хазяїн. Я - %s. Я скучив!\n", getNickname());
    }

    @Override
    public void foul() {
        System.out.println("Потрібно добре замести сліди...");
    }
}
