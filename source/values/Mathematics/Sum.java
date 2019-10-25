package values.Mathematics;


import values.*;

public class Sum extends Mathematic {
    private Value[] args;
    
    public Sum (Value[] args) throws Exception {
        this.args = args;
    }

    public String getOriginal () {
        return "sum";
    }

    public String executeMath() throws Exception {
        return Double.toString(Double.parseDouble(this.args[0].getValue()) + Double.parseDouble(this.args[1].getValue()));
    }
}