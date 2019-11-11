package operations;

import java.io.BufferedReader;
import java.util.Arrays;

import values.Scope;
import values.Value;

public class Call extends Operation{
    private Value[] args;

    public Call (Value[] args, BufferedReader br) {
        this.args = args;
    }

    public OperationResult execute (Scope scope) throws Exception {
        if (this.args.length < 1) {
            throw new Exception("Operation \"call\" expect at least 1 parameter, received " + args.length);
        }
        
        String functionName = this.args[0].getOriginal();
        Function function = Function.getFunction(functionName);
        if (function == null) {
            throw new Exception("Runtime error: Invalid function " + functionName);
        }
        Value[] params = Arrays.copyOfRange(this.args, 1, this.args.length);
        function.call(params, scope);
        return null;
    }
}