package services;

import java.util.ArrayList;
import java.util.Arrays;

import values.*;
import values.Mathematics.*;
import values.Conditions.*;

public class LexerExpression {
    public static Value[] getExpressions (String[] args) throws Exception {
        ArrayList<Value> expressions = new ArrayList<Value>();

        String[] params = args.clone();

        int lastIteration = 0;

        while (lastIteration <= params.length) {
            params = Arrays.copyOfRange(params, lastIteration, params.length);

            if (params.length == 0) {
                break;
            }

            if (LexerExpression.isStringExpression(params[0])) {
                lastIteration = LexerExpression.handleString(params, expressions);
                continue;
            }

            if (LexerExpression.isNumberExpression(params[0])) {
                lastIteration = LexerExpression.handleNumber(params, expressions);
                continue;
            }

            if (LexerExpression.isMathExpression(params[0])) {
                lastIteration = LexerExpression.handleMath(params, expressions);
                continue;
            }

            if (LexerExpression.isConditionExpression(params[0])) {
                lastIteration = LexerExpression.handleCondition(params, expressions);
                continue;
            }

            if (params[0].equals("call")) {
                lastIteration = LexerExpression.handleCall(params, expressions);
                continue;
            }

            if (params[0].equals("input")) {
                expressions.add(new Input());
                lastIteration = 1;
                continue;
            }

            expressions.add(new Variable(params[0]));
            lastIteration = 1;
        }
        return expressions.toArray(new Value[expressions.size()]);
    }

    public static Value getMathExpression(String arg, Value[] MathParam){
        try{
            switch (arg) {
                case "sum":
                    return new Sum(MathParam);
                case "subtraction":
                case "sub":
                    return new Subtraction(MathParam);
                case "multiplication":
                case "mult":
                    return new Multiplication(MathParam);   
                case "division":
                case "div":
                    return new Division(MathParam);    
                case "mod":
                    return new Mod(MathParam);
                case "pow":
                    return new Power(MathParam); 
                case "root":
                    return new Root(MathParam);
            }
        }catch( Exception e ){
            System.out.println("Math expression construction failed");
        }
        return null;
    }

    public static Value getConditionExpression(String arg, Value[] params){
        try{
            switch (arg) {
                case "equals":
                    return new Equals(params);
                case "diff":
                case "different":
                    return new Different(params);
                case "greater":
                    return new Greater(params);
                case "greaterequals":
                    return new GreaterEquals(params);
                case "less":
                    return new Less(params);
                case "lessequals":
                    return new LessEquals(params);
                case "and":
                    return new And(params);
                case "or":
                    return new Or(params);
                case "not":
                    return new Not(params);
            }
        }catch( Exception e ){
            System.out.println("Condition expression construction failed");
        }
        return null;
    }

    private static Boolean isStringExpression (String arg) {
        Boolean isStartInQuote = arg.charAt(0) == '"';
        return isStartInQuote;
    }

    private static Boolean isNumberExpression (String arg) {
        boolean isNumeric = arg.chars().allMatch( Character::isDigit );
        return isNumeric;
    }

    private static Boolean isMathExpression (String arg) {
        Boolean isReserved = Mathematic.reservedWords.contains(arg);
        return isReserved;
    }

    private static Boolean isConditionExpression (String arg) {
        Boolean isReserved = Condition.reservedWords.contains(arg);
        return isReserved;
    }

    private static int handleString (String[] args, ArrayList<Value> expressions) throws Exception {
        String message = args[0];
        Value value = new Constant(message.substring(1, message.length() - 1));
        expressions.add(value);
        return 1;
    }

    private static int handleNumber (String[] args, ArrayList<Value> expressions) throws Exception {
        String arg = args[0];
        boolean isNumeric = arg.chars().allMatch(Character::isDigit);
        if (!isNumeric) {
            throw new Exception("Error: invalid variable: " + arg);
        }
        expressions.add(new Constant(arg));
        return 1;
    }

    private static int handleMath (String[] args, ArrayList<Value> expressions) throws Exception {
        String[] params = Arrays.copyOfRange(args, 1, args.length);
        Value[] values = LexerExpression.getExpressions(params);

        String operation = args[0];
        Value expression = LexerExpression.getMathExpression(operation, values);
        expressions.add(expression);

        for (int i = 2; i < values.length; i++) {
            expressions.add(values[i]);
        }

        return args.length;
    }

    private static int handleCondition (String[] args, ArrayList<Value> expressions) throws Exception {
        String[] params = Arrays.copyOfRange(args, 1, args.length);
        Value[] values = LexerExpression.getExpressions(params);

        String operation = args[0];
        Value expression = LexerExpression.getConditionExpression(operation, values);
        expressions.add(expression);

        int startI = 2;
        if (operation.equals("not")) {
            startI = 1;
        }        

        for (int i = startI; i < values.length; i++) {
            expressions.add(values[i]);
        }

        return args.length;
    }

    private static int handleCall (String[] args, ArrayList<Value> expressions) throws Exception {
        Value value = new CallExpression(args); 

        expressions.add(value);
        return args.length;
    }
}