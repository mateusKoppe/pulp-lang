import java.io.BufferedReader;

class Show extends Operation{
    private Value[] args;

    public Show (Value[] args, Scope scope, BufferedReader br) {
        this.args = args;
    }

    public void execute () {
        System.out.println(this.args[0].getValue());
    }
}