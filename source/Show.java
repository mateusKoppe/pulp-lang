import java.io.BufferedReader;

class Show extends Operation{
    private String[] args;

    public Show (String[] args, BufferedReader br) {
        this.args = args;
    }

    public void execute () {
        System.out.println(this.args[0]);
    }
}