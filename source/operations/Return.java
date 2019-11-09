package operations;

import java.io.BufferedReader;
import values.Value;
import values.Scope;

public class Return extends Operation {
    private Value[] args;

    public Return (Value[] args, BufferedReader br) throws Exception {
        if (args.length != 1) {
            throw new Exception("Operation \"return\" got and syntax error. \n" +
                "The right syntax is: return <value>"
            );
        }
        this.args = args;
    }

    public OperationResult execute (Scope scope) throws Exception {
        try {
            OperationResult result = new OperationResult("return");
            result.value = this.args[0];
            return result;
        } catch (Exception e) {
            throw e;
        }
    }
}