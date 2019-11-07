package operations;

import java.io.BufferedReader;
import values.Value;
import values.Scope;

public class Return extends Operation {
    private Value[] args;
    private Scope scope;

    public Return (Value[] args, Scope scope, BufferedReader br) throws Exception {
        if (args.length != 1) {
            throw new Exception("Operation \"return\" got and syntax error. \n" +
                "The right syntax is: return <value>"
            );
        }
        this.args = args;
        this.scope = scope;
    }

    public OperationResult execute () throws Exception {
        try {
            OperationResult result = new OperationResult("return");
            result.value = this.args[0];
            return result;
        } catch (Exception e) {
            throw e;
        }
    }
}