import java.util.Scanner;

/**
 * description
 *
 * @author Alexander Isai on 18.03.2024.
 */
public class DailySchedule {

    static String[][] schedule = initSchedule();
    static String exit = "Exit";
    static String change = "Change";

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        getAnswer();

    }

    static String[][] initSchedule(){
        String[][] schedule = new String[7][2];
        schedule[0][0] = "Sunday";
        schedule[0][1] = "have a rest, play Volleyball";
        schedule[1][0] = "Monday";
        schedule[1][1] = "Make java-homework; create working plan";
        schedule[2][0] = "Tuesday";
        schedule[2][1] = "Something read; visit online-lesson";
        schedule[3][0] = "Wednesday";
        schedule[3][1] = "Work with the project; job searching";
        schedule[4][0] = "Thursday";
        schedule[4][1] = "English/German learn; visit online-lesson; shopping";
        schedule[5][0] = "Friday";
        schedule[5][1] = "Make java-homework; meet with friends";
        schedule[6][0] = "Saturday";
        schedule[6][1] = "Visit online-lesson, make homework";
        return schedule;
    }

    static String getRequestDay(){

        String requestDay;
        do {
            System.out.println("Please, input the day of the week:");
            requestDay = scanner.nextLine().trim();
        } while (!isRequestCorrect(requestDay));
        return requestDay;
    }

    static boolean isRequestCorrect(String request){
        boolean isCorrect = false;
        String requestFormatted = formatString(request);
        for (String[] strings : schedule) {
            if (requestFormatted.equals(strings[0]) || requestFormatted.equals(exit) || isChangeCorrect(requestFormatted)) {
                isCorrect = true;
                break;
            }
        }
        if(!isCorrect){
            System.out.println("Sorry, I don't understand you, please try again.");
        }
        return isCorrect;
    }

    static boolean isChangeCorrect(String strChange){
        boolean checkChange = false;
        String[] changing = strChange.split(" ");
        for (String[] strings : schedule) {
            if (changing.length > 1 && strings[0].equals(formatString(changing[1]))) {
                checkChange = true;
                break;
            }
        }
        return (changing[0].equals(change) && checkChange);
    }

    static String formatString(String str){
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    static void getAnswer() {
        String requestDay = formatString(getRequestDay());
        while (!requestDay.equals(exit)){
            if (requestDay.contains("Change")){
                changingPlans(requestDay);
            }
            for (String[] strings : schedule) {
                if (strings[0].equals(requestDay)) {
                    System.out.printf("Your tasks for %s", strings[0] + ": " + strings[1] + "\n");
                }
            }
            requestDay = formatString(getRequestDay());
        }
        System.out.println("Good buy!");
    }

    static void changingPlans(String strCh){
        String changeDay = formatString(strCh.split(" ")[1]);
        System.out.printf("Please, input new tasks for %s", changeDay + "\n");
        String newTask = scanner.nextLine().trim();
        for (int i = 0; i < schedule.length; i++) {
            if (schedule[i][0].equals(changeDay)){
                schedule[i][1] = newTask;
                break;
            }
        }
    }

}
