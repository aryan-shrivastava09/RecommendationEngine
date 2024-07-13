import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class EmployeeChoiceMenu {

    private final PrintWriter out;
    private final BufferedReader in;
    private final BufferedReader stdIn;

    private final String employeeId;

    public EmployeeChoiceMenu(PrintWriter out, BufferedReader in, BufferedReader stdIn, String employeeId) {
        this.out = out;
        this.in = in;
        this.stdIn = stdIn;
        this.employeeId = employeeId;
    }

    public void displayEmployeeMenu() throws IOException {
        Map<String, MenuFunction> commands = initializeCommands();

        String choice = "";
        while (!choice.equals("6")) {
            printMenu();
            choice = stdIn.readLine();

            if (commands.containsKey(choice)) {
                commands.get(choice).execute();
            } else if ("6".equals(choice)) {
                logoutHandler();
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    private void printMenu() {
        System.out.println("Employee Menu:");
        System.out.println("1. Vote for Next Day Recommendation");
        System.out.println("2. Give Feedback to Chef");
        System.out.println("3. View Notifications");
        System.out.println("4. Give Feedback for Discarded Items");
        System.out.println("5. Update you Profile");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private Map<String, MenuFunction> initializeCommands() {
        Map<String, MenuFunction> commands = new HashMap<>();
        commands.put("1", new VoteForRecommendationFunction(stdIn, out, in,employeeId));
        commands.put("2", new FeedbackToChefFunction(stdIn, out, in,employeeId));
        commands.put("3", new ViewNotificationsFunction(out, in));
        commands.put("4", new DiscardMenuFunction(out, in, stdIn,"employee"));
        commands.put("5", new UpdateProfileFunction(out, in, stdIn, employeeId));
        return commands;
    }

    private void logoutHandler(){
        ClientCafeteria.sendUserSessionRequest(employeeId, "logout");
    }
}
