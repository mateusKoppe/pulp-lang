package operations;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import values.Scope;
import values.Value;
import services.Lexer;

public class Function extends Operation {
    private Value[] args;
    private String[] params;
    private Scope staticScope;
    private List<String> paramsName;
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

        this.staticScope = new Scope(parentScope);
        this.reader = br;
        this.name = this.args[0].getOriginal();
        this.paramsName = new ArrayList<String>();

        this.loadParams();

        try {
            this.read();
        } catch (Exception e) {
            throw e;
        }
    }

    public OperationResult call (Value[] values, Scope scope) throws Exception {
        try {
            this.scope = new Scope(scope);
            for (int i = 0; i < this.params.length; i++) {
                if (i < values.length) {
                    this.scope.declareVariable(this.paramsName.get(i), values[i].getValue(scope));
                } else {
                    this.scope.declareVariable(this.paramsName.get(i), "");
                }
            }
            return this.lexer.run(this.scope);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public OperationResult execute (Scope scope) throws Exception {
        try {
            scope.declareFunction(this.name, this);
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    private void loadParams () throws Exception {
        if (!this.args[3].getOriginal().equals("params")) {
            this.params = new String[0];
            return;
        }

        int paramsLength = this.args.length - 5;
        if (paramsLength == 0) {
            throw new Exception(
                "Syntax error inside function:" + this.name + "\n" +
                "  keyword \"params\" used, but no parameter set"
            );
        }

        String[] newParams = new String[paramsLength];
        for (int i = 4; !this.args[i].getOriginal().equals("do"); i++) {
            newParams[i - 4] = this.args[i].getOriginal();
        }
        this.params = newParams;
    }
    
    public void read () throws Exception {
        try {
            this.lexer = new Lexer(this.reader, this.staticScope);
            for (int i = 0; i < this.params.length; i++) {
                this.paramsName.add(this.params[i]);
            }
        } catch (Exception e) {
            throw new Exception("Syntax error inside function: " + this.name + "\n" + e.getMessage());
        }
    };
}