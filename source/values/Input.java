package values;

import java.util.Scanner;

public class Input extends Value {
    private String value;
    private Scanner scanner;

    public Input () {
        this.value = "input";
        this.scanner = new Scanner(System.in);
    }

    public String getOriginal () {
        return this.value;
    }

    public String getValue () {
        return this.scanner.nextLine();
    }

}