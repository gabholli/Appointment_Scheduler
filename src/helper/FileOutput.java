package helper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * Class containing methods for outputting login information to a text file
 */
public class FileOutput {

    /**
     * Method used to write to text file if login is successful
     * @param userName Parameter used to access username typed into username text field on login screen
     * @throws IOException For throwing IOException
     */
    public static void writeToFileSuccess(String userName) throws IOException {
        String filename = "login_activity.txt";
        String item;
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);

        Scanner input = new Scanner("A successful login attempt occurred for Username: " + userName + " on " + localDate + " at " + localTime);

        FileWriter fWriter = new FileWriter(filename, true);

        PrintWriter outputFile = new PrintWriter(fWriter);

        item = input.nextLine();
        outputFile.println(item);
        outputFile.close();
    }

    /**
     * Method used to write to text file if login attempt fails
     * @param userName Parameter used to access username typed into username text field on login screen
     * @throws IOException For throwing IOException
     */
    public static void writeToFileFailure(String userName) throws IOException {
        String filename = "login_activity.txt";
        String item;
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);

        if (userName.isBlank()) {
            Scanner input = new Scanner("A failed login attempt occurred for an unknown user on " + localDate + " at " + localTime);
            FileWriter fWriter = new FileWriter(filename, true);
            PrintWriter outputFile = new PrintWriter(fWriter);

            item = input.nextLine();
            outputFile.println(item);
            outputFile.close();
        } else {
            Scanner input = new Scanner("A failed login attempt occurred for Username: " + userName + " on " + localDate + " at " + localTime);
            FileWriter fWriter = new FileWriter(filename, true);
            PrintWriter outputFile = new PrintWriter(fWriter);

            item = input.nextLine();
            outputFile.println(item);
            outputFile.close();
        }

    }
}
