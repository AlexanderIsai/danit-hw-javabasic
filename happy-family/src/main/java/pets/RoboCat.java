package pets;

/**
 * description
 *
 * @author Alexander Isai on 24.03.2024.
 */
public class RoboCat extends Pet{

    public RoboCat(String nickname, int age, int trickLevel, String[] habits) {
        super(nickname, age, trickLevel, habits);
        this.species = Species.ROBOCAT;
    }

    @Override
    public void respond() {
        System.out.printf("Привіт! Я кіт...робокіт. Та моє ім'я %s\n",  getNickname());
    }
}
