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

    public Function (Value[] args, Scope parentScope, BufferedReader br) {
        this.args = args;
        this.scope = new Scope(parentScope);
        this.reader = br;
        this.name = this.args[0].getOriginal();
        this.read();
    }

    public void call () {
        this.lexer.run();
    }
    
    public void execute () {
    }
    
    public void read () {
        this.lexer = new Lexer(this.reader, this.scope);
        this.scope.getParent().declareFunction(this.name, this);
    };
}