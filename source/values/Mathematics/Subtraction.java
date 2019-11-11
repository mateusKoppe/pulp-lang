package values.Mathematics;


import values.*;

public class Subtraction extends Mathematic {
    private Value[] args;

    public Subtraction (Value[] args) throws Exception {
        this.args = args;
    }

    public String getOriginal () {
        return "Subtraction";
    }

    public String executeMath(Scope scope) throws Exception {
        Double param1 = Double.parseDouble(this.args[0].getValue(scope));
        Double param2 = Double.parseDouble(this.args[1].getValue(scope));
        Double result = param1 - param2;
        return Double.toString(result);
    }
}