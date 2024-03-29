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

    public Lexer (BufferedReader br) throws Exception{
        this.reader = br;
        this.commands = new ArrayList<Operation>();

        try {
            this.read();
        } catch (Exception e) {
            throw e;
        }
    }

    public OperationResult execute () {
        try {
            for (int i = 0; i < this.commands.size(); i++) {
                OperationResult result = this.commands.get(i).execute(this.scope);
                if (result != null && result.type.equals("return")) {
                    return result;
                }
            }
        } catch (Exception e) {
            System.out.println("");
            System.err.println(e.getMessage());
            System.err.println("Proccess killed...");
        }
        return null;
    }

    public Operation generateOperation (String[] words) throws Exception {
        String token = words[0];
        Boolean isComment = token.substring(0, 2).equals("//");
        if (isComment) {
            return null;
        }
        String[] args = Arrays.copyOfRange(words, 1, words.length);
        try {
            Value[] params = LexerExpression.getExpressions(args);
            switch (token) {
                case "show":
                    return new Show(params, this.reader);
                case "declare":
                    if (params.length > 3 && params[2].getOriginal().equals("function")) {
                        return new Function(params, this.reader);
                    }
                    return new Declare(params);
                case "set":
                    return new Set(params, this.reader);
                case "call":
                    return new Call(params);
                case "return":
                    return new Return(params, this.reader);
                case "if":
                    return new If(params, this.reader);
                case "repeat":
                    return new Repeat(params, this.reader);
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

    public OperationResult run (Scope scope) {
        this.scope = scope;
        return this.execute();
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