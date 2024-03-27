package pets;

import jdk.jshell.DeclarationSnippet;

import java.util.Set;

/**
 * description
 *
 * @author Alexander Isai on 24.03.2024.
 */
public class Fish extends Pet {

    public Fish(String nickname, int age, int trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
        this.species = Species.FISH;
    }

    @Override
    public void respond() {
        System.out.printf("Привіт! Я рибка та вмію говорити, але якщо б вміла то сказав би, що мене звуть %s\n",  getNickname());
    }
}
