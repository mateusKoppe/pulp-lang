package services;

import operations.*;
import values.*;
import values.Mathematics.*;
import java.util.ArrayList;
import java.util.Arrays;

class LexerExpression {
    public static Value[] getExpressions (String[] args, Scope scope) throws Exception {
        ArrayList<Value> expressions = new ArrayList<Value>();

        for (int i = 0; i < args.length; i++) {
            String[] params = Arrays.copyOfRange(args, i, args.length);

            if (LexerExpression.isStringExpression(args[i])) {
                i = LexerExpression.handleString(params, expressions);
                continue;
            }

            if (LexerExpression.isNumberExpression(args[1])) {
                i = LexerExpression.handleNumber(params, expressions);
                continue;
            }

            if (LexerExpression.isMathExpression(args[i])) {
                i = LexerExpression.handleNumber(params, expressions);
                continue;
            }
        }
        return expressions.toArray(new Value[expressions.size()]);
    }

    public static Value getMathExpression(Value[] MathParam, String arg){ //rever o retorno
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
                case "sqrt":
                    return new Root(MathParam);
            }
        }catch( Exception e ){
            System.out.println("Math expression construction failed");
            //getMathExpression(args, i);
        }
        return null; // O Java tava me pedindo um return aqui (?) fiquei confuso
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
        Boolean isReserved = Mathematic.reservatedWords.contains(arg);
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
}