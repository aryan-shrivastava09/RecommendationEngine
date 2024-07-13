import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class ChefMenuFunction implements RoleMenuFunction {
    @Override
    public void execute(BufferedReader stdIn,PrintWriter out, BufferedReader in, String employeeId) throws IOException {
        ChefChoiceMenu chefMenu = new ChefChoiceMenu(out,in,stdIn,employeeId);
        chefMenu.displayChefMenu();
    }
}