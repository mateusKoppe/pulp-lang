package operations;

import java.io.BufferedReader;
import values.Value;
import values.Scope;

public class Show extends Operation{
    private Value[] args;

    public Show (Value[] args, BufferedReader br) throws Exception {
        if (args.length != 1) {
            throw new Exception("Operation \"show\" expect 1 parameter, received " + args.length);
        }
        this.args = args;
    }

    public OperationResult execute (Scope scope) throws Exception {
        System.out.println(this.args[0].getValue(scope));
        return null;
    }
}