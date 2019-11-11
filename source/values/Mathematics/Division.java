package values.Mathematics;


import values.*;

public class Division extends Mathematic {
    private Value[] args;

    public Division (Value[] args) throws Exception {
        this.args = args;
    }

    public String getOriginal () {
        return "Division";
    }

    public String executeMath(Scope scope) throws Exception {
            return Double.toString(Double.parseDouble(this.args[0].getValue(scope)) / Double.parseDouble(this.args[1].getValue(scope)));
    }
    
}