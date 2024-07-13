import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class EmployeeMenuFunction implements RoleMenuFunction {
    @Override
    public void execute(BufferedReader stdIn,PrintWriter out, BufferedReader in, String employeeId) throws IOException {
        EmployeeChoiceMenu employeeMenu = new EmployeeChoiceMenu(out, in, stdIn,employeeId);
        employeeMenu.displayEmployeeMenu();
    }
}