import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ChefChoiceMenu {

    private final PrintWriter out;
    private final BufferedReader in;
    private final BufferedReader stdIn;
    private final String employeeID;

    public ChefChoiceMenu(PrintWriter out, BufferedReader in, BufferedReader stdIn, String employeeID) {
        this.out = out;
        this.in = in;
        this.stdIn = stdIn;
        this.employeeID = employeeID;
    }

    public void displayChefMenu() throws IOException {
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
                System.out.println("Invalid choice, Try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("Chef Choice Menu:");
        System.out.println("1. View Food Menu");
        System.out.println("2. View Top Recommendations");
        System.out.println("3. Roll Out Next Day Menu");
        System.out.println("4. View Voted Report");
        System.out.println("5. Discard Menu Item List");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private Map<String, MenuFunction> initializeCommands() {
        Map<String, MenuFunction> commands = new HashMap<>();
        commands.put("1", new ViewMenuFunction(out, in));
        commands.put("2", new ViewTopRecommendationsFunction(stdIn, out, in));
        commands.put("3", new RollOutNextDayMenuFunction(stdIn, out, in));
        commands.put("4", new ViewVotedReportFunction(out, in));
        commands.put("5", new DiscardMenuFunction(out, in, stdIn,"chef"));
        return commands;
    }

    private void logoutHandler(){
        ClientCafeteria.sendUserSessionRequest(employeeID, "logout");
    }

    private String promptString(String prompt) throws IOException {
        System.out.print(prompt);
        return stdIn.readLine();
    }
}
