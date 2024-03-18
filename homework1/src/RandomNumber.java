import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RandomNumber {
    public static void main(String[] args) {

        System.out.println("Let the game begin!");
        playGame();
    }

    static void playGame(){
        int[] numbers = new int[0];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name");
        String name = scanner.nextLine();
        int secretNumber = randomIntGenerator();
        System.out.println(secretNumber);
        int playerChoice = -1;
        while (playerChoice != secretNumber){
            System.out.println("Input pls your number");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf("\"%s\" - это не число. Пожалуйста, введите число:\n", input);
            }
            playerChoice = scanner.nextInt();
            compareNumbers(secretNumber, playerChoice);
            numbers = growArray(numbers);
            numbers[numbers.length - 1] = playerChoice;
        }
        reverseSortArray(numbers);
        System.out.println("Congratulations, " + name + "! You won " + playerChoice + " = " + secretNumber);
        System.out.println("your numbers - " + Arrays.toString(numbers));
    }
    static int randomIntGenerator(){
        Random random = new Random();
        return random.nextInt(0, 101);
    }
    static void compareNumbers(int secret, int choice){
        if (secret > choice){
            System.out.println("Your number is too small. Please, try again.");
        }
        if (secret < choice){
            System.out.println("Your number is too big. Please, try again.");
        }
    }
    static int[] growArray(int[] array){
        return Arrays.copyOf(array, array.length + 1);
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