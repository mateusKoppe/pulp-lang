package operations;

import java.io.BufferedReader;

import values.Scope;
import values.Value;

public class Call extends Operation{
    private Value[] args;
    private Scope scope;

    public Call (Value[] args, Scope scope, BufferedReader br) {
        this.args = args;
        this.scope = scope;
    }

    public void execute () throws Exception {
        if (this.args.length != 1) {
            throw new Exception("Operation \"call\" expect 1 parameter, received " + args.length);
        }
        
        String functionName = this.args[0].getOriginal();
        Function function = this.scope.getFunction(functionName);
        if (function == null) {
            throw new Exception("Runtime error: Invalid function " + functionName);
        }
        this.scope.getFunction(functionName).call();
    }
}