package operations;

import java.io.BufferedReader;

import values.Scope;
import values.Value;
import services.Lexer;

public class Repeat extends Operation {
    private Value[] args;
    private Value condition;
    private BufferedReader reader;
    private Lexer lexer;

    public Repeat (Value[] args, BufferedReader br) throws Exception {
        this.args = args;
        if (!this.args[0].getOriginal().equals("if") ||
            !this.args[this.args.length - 1].getOriginal().equals("do")) {
            throw new Exception("Operation \"repeat if\" got and syntax error. \n" + 
                "The right syntax is: repeat if <expression> do"
            );
        }

        this.condition = args[1];

        this.reader = br;
        

        try {
            this.read();
        } catch (Exception e) {
            throw e;
        }
    }

    public OperationResult execute (Scope scope) throws Exception {
        String value = this.condition.getValue(scope);
        Double condition = Double.parseDouble(value);
        while (condition != 0) {
            OperationResult result = this.lexer.run(scope);
            if (result != null && result.type.equals("return")) {
                return result;
            }
            value = this.condition.getValue(scope);
            condition = Double.parseDouble(value);
        }
        return null;
    }

    public void read () throws Exception {
        try {
            this.lexer = new Lexer(this.reader);
        } catch (Exception e) {
            throw new Exception("Syntax error inside if\n" + e.getMessage());
        }
    };
}