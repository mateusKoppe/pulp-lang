package operations;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import values.Scope;
import values.Value;
import services.Lexer;
import services.LexerExpression;

public class If extends Operation {
    private Value[] args;
    private Value condition;
    private BufferedReader reader;
    private Lexer lexer;

    public If (Value[] args, BufferedReader br) throws Exception {
        this.args = args;
        if (!this.args[this.args.length - 1].getOriginal().equals("do")) {
            throw new Exception("Operation \"if\" got and syntax error. \n" + 
                "The right syntax is: if <expression> do"
            );
        }

        this.condition = args[0];

        this.reader = br;
        

        try {
            this.read();
        } catch (Exception e) {
            throw e;
        }
    }

    public OperationResult execute (Scope parent) throws Exception {
        Scope scope = new Scope(parent);
        String value = this.condition.getValue(scope);
        Double condition = Double.parseDouble(value);
        if (condition != 0) {
            return this.lexer.run(scope);
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