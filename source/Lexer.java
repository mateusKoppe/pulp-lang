import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Lexer {
    private BufferedReader reader;
    private List<Operation> commands;

    public Lexer (BufferedReader br) {
        this.reader = br;
        this.commands = new ArrayList<Operation>();
    }

    public void execute () {
        for (int i = 0; i < this.commands.size(); i++) {
            this.commands.get(i).execute();
        }
    }

    public Operation generateOperation (String[] args) {
        String token = args[0];
        String[] params = Arrays.copyOfRange(args, 1, args.length);
        switch (token) {
            case "show":
                return new Show(params, this.reader);
        }
        return null;
    }
    
    public void read () {
        String line;
        try {
            while ((line = this.reader.readLine()) != null) {
                String[] args = this.getArgs(line);
                Operation operation = this.generateOperation(args);
                if (operation != null) {
                    this.commands.add(operation);
                }
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run () {
        this.read();
        this.execute();
    }

    private String[] getArgs(String line) {
        String[] args = line.split(" ");
        List<String> expressions = new ArrayList<String>();

        Boolean isBuildingString = false;
        String temp = "";

        for (int i = 0; i < args.length; i++) {
            String word = args[i];
            Boolean isQuoteStart = word.charAt(0) == '"';
            Boolean isQuoteEnd = word.charAt(word.length() - 1) == '"';
            if (!isBuildingString && !isQuoteStart) {
                expressions.add(word);
            }
            if (!isBuildingString && isQuoteStart) {
                temp = word;
                isBuildingString = true;
            }
            if (isBuildingString && !isQuoteStart) {
                temp = temp + " " + word;
            }
            if (isBuildingString && isQuoteEnd) {
                temp = temp.substring(1, temp.length() - 1);
                expressions.add(temp);
                isBuildingString = false;
                temp = "";
            }
        }

        return expressions.toArray(new String[expressions.size()]);
    }
}