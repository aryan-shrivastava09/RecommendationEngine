import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class AdminChoiceMenu {

    private final PrintWriter out;
    private final BufferedReader in;
    private final BufferedReader stdIn;

    private final String employeeID;

    public AdminChoiceMenu(PrintWriter out, BufferedReader in, BufferedReader stdIn, String employeeID) {
        this.out = out;
        this.in = in;
        this.stdIn = stdIn;
        this.employeeID = employeeID;
    }

    public void displayAdminMenu() throws IOException {
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
        System.out.println("Admin Choice Menu:");
        System.out.println("1. View Food Menu");
        System.out.println("2. Add Item in Food Menu");
        System.out.println("3. Update Item in Food Menu");
        System.out.println("4. Delete Item in Food Menu");
        System.out.println("5. Discard Menu Item List");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private Map<String, MenuFunction> initializeCommands() {
        Map<String, MenuFunction> commands = new HashMap<>();
        commands.put("1", new ViewMenuFunction(out, in));
        commands.put("2", new AddMenu(stdIn, out, in));
        commands.put("3", new UpdateMenuFunction(stdIn, out, in));
        commands.put("4", new DeleteMenuFunction(stdIn, out, in));
        commands.put("5", new DiscardMenuFunction(out, in, stdIn,"admin"));
        return commands;
    }

    private void logoutHandler(){
        ClientCafeteria.sendUserSessionRequest(employeeID, "logout");
    }
}
