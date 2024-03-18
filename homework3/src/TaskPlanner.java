import java.util.Scanner;

/**
 * description
 *
 * @author Alexander Isai on 18.03.2024.
 */
public class TaskPlanner {

    static String[][] schedule = initSchedule();
    static String exit = "Exit";

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
        Scanner scanner = new Scanner(System.in);
        String requestDay;
        do {
            System.out.println("Please, input the day of the week:");
            requestDay = scanner.next().trim();
        } while (!isRequestCorrect(requestDay));
        return requestDay;
    }

    static boolean isRequestCorrect(String request){
        boolean isCorrect = false;
        String requestFormatted = formatString(request);
        for (String[] strings : schedule) {
            if (requestFormatted.equals(strings[0]) || requestFormatted.equals(exit)) {
                isCorrect = true;
                break;
            }
        }
        if(!isCorrect){
            System.out.println("Sorry, I don't understand you, please try again.");
        }
        return isCorrect;
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
            switch (requestDay){
                case "Sunday": System.out.println("Your tasks for Sunday: " + schedule[0][1]); break;
                case "Monday": System.out.println("Your tasks for Monday: " + schedule[1][1]); break;
                case "Tuesday": System.out.println("Your tasks for Tuesday: " + schedule[2][1]); break;
                case "Wednesday": System.out.println("Your tasks for Wednesday: " + schedule[3][1]); break;
                case "Thursday": System.out.println("Your tasks for Thursday: " + schedule[4][1]); break;
                case "Friday": System.out.println("Your tasks for Friday: " + schedule[5][1]); break;
                case "Saturday": System.out.println("Your tasks for Saturday: " + schedule[6][1]); break;
                default:
            }
            requestDay = formatString(getRequestDay());
        }
        System.out.println("Good buy!");
    }

}
