import java.io.BufferedReader;

class Declare extends Operation{
    private Value[] args;
    private Scope scope;

    public Declare (Value[] args, Scope scope, BufferedReader br) {
        this.args = args;
        this.scope = scope;
    }

    public void execute () {
        this.scope.declareVariable(this.args[0].getOriginal(), this.args[2].getValue());
    }
}