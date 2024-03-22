import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RandomNumber {

    static Random random = new Random();
    public static void main(String[] args) {

        System.out.println("Let the game begin!");
        playGame();
    }

    static void playGame(){
        int[] numbers = new int[0];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name");
        String name = scanner.nextLine();
        int secretNumber = random.nextInt(0, 101);
        System.out.println(secretNumber);
        int playerChoice = -1;
        while (playerChoice != secretNumber){
            System.out.println("Input pls your number");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf("\"%s\" - это не число. Пожалуйста, введите число:\n", input);
            }
            playerChoice = scanner.nextInt();
            if (Integer.compare(playerChoice, secretNumber) < 0){
                System.out.println("Your number is too small. Please, try again.");
            } else if (Integer.compare(playerChoice, secretNumber) > 0) {
                System.out.println("Your number is too big. Please, try again.");
            }

            numbers = Arrays.copyOf(numbers, numbers.length + 1);
            numbers[numbers.length - 1] = playerChoice;
        }
        reverseSortArray(numbers);
        System.out.println("Congratulations, " + name + "! You won " + playerChoice + " = " + secretNumber);
        System.out.println("your numbers - " + Arrays.toString(numbers));
    }
    static void reverseSortArray(int[] array){
        int n = array.length;
        int temp = 0;
        for(int i = 0; i < n; i++){
            for(int j = 1; j < (n-i); j++){
                if(array[j-1] < array[j]){
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

}