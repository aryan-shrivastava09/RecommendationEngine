import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class AdminMenuFunction implements RoleMenuFunction {
    @Override
    public void execute(BufferedReader stdIn,PrintWriter out, BufferedReader in, String employeeId) throws IOException {
        AdminChoiceMenu adminMenu = new AdminChoiceMenu(out,in,stdIn,employeeId);
        adminMenu.displayAdminMenu();
    }
}