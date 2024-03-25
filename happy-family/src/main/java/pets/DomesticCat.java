package pets;

/**
 * description
 *
 * @author Alexander Isai on 24.03.2024.
 */
public class DomesticCat extends Pet implements Foul{

    public DomesticCat(String nickname, int age, int trickLevel, String[] habits) {
        super(nickname, age, trickLevel, habits);
        this.species = Species.DOMESTICCAT;
    }
    @Override
    public void respond() {
        System.out.printf("Привіт! Я твій кіт %s\n",  getNickname());
    }

    @Override
    public void foul() {
        System.out.println("Скажу, що це не я...");
    }
}
