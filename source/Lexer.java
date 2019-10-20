import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Lexer {
    private BufferedReader reader;
    private Scope scope;
    private List<Operation> commands;

    public Lexer (BufferedReader br, Scope scope) {
        this.reader = br;
        this.scope = scope;
        this.commands = new ArrayList<Operation>();
    }

    public void execute () {
        for (int i = 0; i < this.commands.size(); i++) {
            this.commands.get(i).execute();
        }
    }

    public Value[] generateExpressions (String[] args) {
        ArrayList<Value> expressions = new ArrayList<Value>();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            int argLastIndex = arg.length() - 1;
            Boolean isString = arg.charAt(0) == '"' && arg.charAt(argLastIndex) == '"';
            if (isString) {
                expressions.add(new Constant(arg.substring(1, argLastIndex)));
            } else {
                expressions.add(new Variable(arg, this.scope));
            }
        }
        return expressions.toArray(new Value[expressions.size()]);
    }

    public Operation generateOperation (String[] words) {
        String token = words[0];
        String[] args = Arrays.copyOfRange(words, 1, words.length);
        Value[] params = this.generateExpressions(args);
        switch (token) {
            case "show":
                return new Show(params, this.scope, this.reader);
            case "declare":
                return new Declare(params, this.scope, this.reader);
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
                expressions.add(temp);
                isBuildingString = false;
                temp = "";
            }
        }

        return expressions.toArray(new String[expressions.size()]);
    }
}