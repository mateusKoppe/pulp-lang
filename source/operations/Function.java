package operations;

import java.io.BufferedReader;

import values.Scope;
import values.Value;
import services.Lexer;

public class Function extends Operation {
    private Value[] args;
    private Scope scope;
    private BufferedReader reader;
    private Lexer lexer;
    private String name;

    public Function (Value[] args, Scope parentScope, BufferedReader br) throws Exception {
        this.args = args;
        if (!this.args[1].getOriginal().equals("to") ||
            !this.args[2].getOriginal().equals("function") ||
            !this.args[this.args.length - 1].getOriginal().equals("do")
        ) {
            throw new Exception("Operation \"declare ... function\" got and syntax error. \n" + 
                "The right syntax is: declare <name> to function [params <param1> [<params2>...]] do"
            );
        }
        this.scope = new Scope(parentScope);
        this.reader = br;
        this.name = this.args[0].getOriginal();
        
        try {
            this.read();
        } catch (Exception e) {
            throw e;
        }
    }

    public void call () {
        this.lexer.run();
    }
    
    public void execute () {
    }
    
    public void read () throws Exception {
        try {
            this.lexer = new Lexer(this.reader, this.scope);
            this.scope.getParent().declareFunction(this.name, this);
        } catch (Exception e) {
            throw new Exception("Syntax error inside function: " + this.name + "\n" + e.getMessage());
        }
    };
}