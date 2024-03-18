import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Shooting2 {

    static Random random = new Random();
    static int size = 3;
    public static void main(String[] args) {

        System.out.println("All set. Get ready to rumble!");
        String[] goal = coordinates();
        System.out.println(Arrays.toString(goal));
        String[][] gameArea = createGameArea();
        getGameArea(gameArea);
        String currentShoot = shooting(goal);
        while (size != 0) {
            getGameArea(checkShooting(gameArea, goal, currentShoot));
            currentShoot = shooting(goal);

        }
        getGameArea(checkShooting(gameArea, goal, currentShoot));
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

    public static boolean isPresentInArray(String[] array, String str){
        boolean answer = false;
        for (String string : array)
            if (string.equals(str)) {
                answer = true;
                break;
            }
        return answer;
    }

    public static String[][] checkShooting(String[][] gameArea, String[] goal, String shoot){
        int[] temp = stringToArray(shoot);
        if (!isPresentInArray(goal, shoot)) {
                    gameArea[temp[0]][temp[1]] = String.valueOf('*');
                }
                else {
                    gameArea[temp[0]][temp[1]] = String.valueOf('x');
                    System.out.println("Есть попадание!!!");
        }
                if (size == 0){
                    System.out.println("УБИЛ!!!");
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

    public static int[] stringToArray(String str){
        int[] shoot = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            shoot[i] = Character.getNumericValue(str.charAt(i));
        }
        return shoot;
    }

    public static String shooting(String[] goal) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите координаты для стрельбы в формате 2-значного числа, где 1-е строка, второе - столбец");
        String shoot = scanner.next();
        for (String string : goal) {
            if (shoot.equals(string)) {
                size--;
            }
        }
        return shoot;
    }
    public static String[] coordinates(){
        int[] randomArray = getArrayGoals(random.nextInt(1, 4));
        StringBuilder sb = new StringBuilder();
        String[] goals = new String[3];
        int temp = random.nextInt(1, 6);
        if (random.nextBoolean()){
            for (int i = 0; i < goals.length; i++) {
                goals[i] = sb.append(randomArray[i]).append(temp).toString();
                sb.setLength(0);
            }
        }
        else {
            for (int i = 0; i < goals.length; i++) {
                goals[i] = sb.append(temp).append(randomArray[i]).toString();
                sb.setLength(0);
            }
        }
        return goals;
    }
    public static int[] getArrayGoals(int goal){
        int[] goals = new int[3];
        for (int i = 0; i < goals.length; i++) {
            goals[i] = goal + i;
        }
        return goals;
    }
}

