import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Shooting {

    public static void main(String[] args) {

        System.out.println("All set. Get ready to rumble!");
        int[] goal = createGoal();
        System.out.println(Arrays.toString(goal));
        String[][] gameArea = createGameArea();
        getGameArea(gameArea);
        int[] shoot = shooting();
        while (!Arrays.equals(goal, shoot)) {
            getGameArea(checkShooting(gameArea, goal, shoot));
            shoot = shooting();
        }
        getGameArea(checkShooting(gameArea, goal, shoot));
    }

    public static String[][] createGameArea() {
        String[][] gameArea = new String[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (i == 0 || j == 0) {
                    gameArea[i][j] = String.valueOf((char) ('0' + (i + j) % 6));
                }
                else {
                    gameArea[i][j] = String.valueOf('-');
                }
            }
        }
        return gameArea;
    }

    public static String[][] checkShooting(String[][] gameArea, int[] goal, int[] shoot){
        if (!Arrays.equals(goal, shoot)) {
            gameArea[shoot[0]][shoot[1]] = String.valueOf('*');
        }
        else {
            gameArea[goal[0]][goal[1]] = String.valueOf('x');
            System.out.println("Есть попадание!!!");
        }
        return gameArea;
    }

    public static void getGameArea(String[][] array) {
        for (String[] strings : array) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(strings[j] + " | ");
            }
            System.out.println();
        }
    }

    public static int[] shooting() {
        int[] shoot = new int[2];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите координаты для стрельбы в формате 2-значного числа, где 1-е строка, второе - столбец");
        int temp = scanner.nextInt();
        shoot[0] = temp / 10;
        shoot[1] = temp % 10;
        return shoot;
    }

    public static int[] createGoal() {
        Random random = new Random();
        int[] goal = new int[2];
        goal[0] = random.nextInt(1, 6);
        goal[1] = random.nextInt(1, 6);

        return goal;
    }
}
