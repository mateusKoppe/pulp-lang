package operations;

import java.io.BufferedReader;
import values.Value;
import values.Scope;

public class Show extends Operation{
    private Value[] args;

    public Show (Value[] args, Scope scope, BufferedReader br) {
        this.args = args;
    }

    public void execute () {
        System.out.println(this.args[0].getValue());
    }
}