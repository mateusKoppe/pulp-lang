package operations;

import java.io.BufferedReader;
import values.Value;
import values.Scope;

public class Declare extends Operation{
    private Value[] args;

    public Declare (Value[] args, BufferedReader br) throws Exception {
        if ((args.length != 1) && !(args.length >= 3 && args[1].getOriginal().equals("to"))) {
            throw new Exception("Operation \"declare\" got and syntax error. \n" +
                "The right syntax is: declare <name> [to <value>]"
            );
        }
        this.args = args;
    }

    public OperationResult execute (Scope scope) throws Exception {
        try {
            if (this.args.length == 1) {
                scope.declareVariable(this.args[0].getOriginal(), "");
            }
            else if (this.args.length >= 3) {
                scope.declareVariable(this.args[0].getOriginal(), this.args[2].getValue(scope));
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
}