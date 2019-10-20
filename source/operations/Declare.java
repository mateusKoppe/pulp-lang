package operations;

import java.io.BufferedReader;
import values.Value;
import values.Scope;

public class Declare extends Operation{
    private Value[] args;
    private Scope scope;

    public Declare (Value[] args, Scope scope, BufferedReader br) {
        this.args = args;
        this.scope = scope;
    }

    public void execute () throws Exception {
        if (this.args.length == 1) {
            this.scope.declareVariable(this.args[0].getOriginal(), "");
        }
        if (this.args.length == 3) {
            this.scope.declareVariable(this.args[0].getOriginal(), this.args[2].getValue());
        }
    }
}