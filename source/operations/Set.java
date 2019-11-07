package operations;

import java.io.BufferedReader;
import values.Value;
import values.Scope;

public class Set extends Operation{
    private Value[] args;
    private Scope scope;

    public Set (Value[] args, Scope scope, BufferedReader br) throws Exception {
        if (args.length != 3 || !args[1].getOriginal().equals("to")) {
            throw new Exception("Operation \"set\" got and syntax error. \n" +
                "The right syntax is: set <name> to <value>"
            );
        }
        this.args = args;
        this.scope = scope;
    }

    public OperationResult execute () throws Exception {
        try {
            this.scope.setVariable(this.args[0].getOriginal(), this.args[2].getValue());
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
}