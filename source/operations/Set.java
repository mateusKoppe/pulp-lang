package operations;

import java.io.BufferedReader;
import values.Value;
import values.Scope;

public class Set extends Operation{
    private Value[] args;

    public Set (Value[] args, BufferedReader br) throws Exception {
        if (args.length != 3 || !args[1].getOriginal().equals("to")) {
            throw new Exception("Operation \"set\" got and syntax error. \n" +
                "The right syntax is: set <name> to <value>"
            );
        }
        this.args = args;
    }

    public OperationResult execute (Scope scope) throws Exception {
        try {
            scope.setVariable(this.args[0].getOriginal(), this.args[2].getValue(scope));
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
}