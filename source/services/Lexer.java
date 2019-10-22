package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import operations.*;
import values.*;

public class Lexer {
    private BufferedReader reader;
    private Scope scope;
    private List<Operation> commands;

    public Lexer (BufferedReader br, Scope scope) throws Exception{
        this.reader = br;
        this.scope = scope;
        this.commands = new ArrayList<Operation>();

        try {
            this.read();
        } catch (Exception e) {
            throw e;
        }
    }

    public void execute () {
        try {
            for (int i = 0; i < this.commands.size(); i++) {
                this.commands.get(i).execute();
            }
        } catch (Exception e) {
            System.out.println("");
            System.err.println(e.getMessage());
            System.err.println("Proccess killed...");
        }
    }

    public Value[] generateExpressions (String[] args) throws Exception {
        ArrayList<Value> expressions = new ArrayList<Value>();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            int argLastIndex = arg.length() - 1;
            Boolean isString = arg.charAt(0) == '"' && arg.charAt(argLastIndex) == '"';
            Boolean isStartNumber = Character.isDigit(arg.charAt(0));
            if (isString) {
                expressions.add(new Constant(arg.substring(1, argLastIndex)));
            } else if (isStartNumber) {
                boolean isNumeric = arg.chars().allMatch( Character::isDigit );
                if (!isNumeric) {
                    throw new Exception("Error: invalid variable: " + arg);
                }
                expressions.add(new Constant(arg));
            } else if (arg.equals("sum")) {
                String[] sumArgs = Arrays.copyOfRange(args, i + 1, args.length);
                Value[] sumParams = this.generateExpressions(sumArgs);
                expressions.add(new Sum(sumParams));
            } else {
                expressions.add(new Variable(arg, this.scope));
            }
        }
        return expressions.toArray(new Value[expressions.size()]);
    }

    public Operation generateOperation (String[] words) throws Exception {
        String token = words[0];
        String[] args = Arrays.copyOfRange(words, 1, words.length);
        try {
            Value[] params = this.generateExpressions(args);
            switch (token) {
                case "show":
                    return new Show(params, this.scope, this.reader);
                case "declare":
                    if (params.length > 3 && params[2].getOriginal().equals("function")) {
                        return new Function(params, this.scope, this.reader);
                    }
                    return new Declare(params, this.scope, this.reader);
                case "set":
                    return new Set(params, this.scope, this.reader);
                case "call":
                    return new Call(params, this.scope, this.reader);
            }
        } catch (Exception e) {
            throw e;
        }
        throw new Exception("Invalid operation \"" + token + "\"");
    }
    
    public void read () throws Exception {
        String line;
        try {
            while ((line = this.reader.readLine()) != null && !line.trim().equals("done")) {
                line = line.trim();
                if (line.equals("")) {
                    continue;
                }
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
        catch(Exception e) {
            throw e;
        }
    }

    public void run () {
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