import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
class AddMenu implements MenuFunction {
    private final BufferedReader stdIn;
    private final PrintWriter out;
    private final BufferedReader in;

    public AddMenu(BufferedReader stdIn, PrintWriter out, BufferedReader in) {
        this.stdIn = stdIn;
        this.out = out;
        this.in = in;
    }

    @Override
    public void execute() throws IOException {
        MenuHandler.addMenu(stdIn, out, in);
    }
}