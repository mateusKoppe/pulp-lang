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

    public String getValue () {
        return Double.toString(Double.parseDouble(this.args[0].getValue()) / Double.parseDouble(this.args[1].getValue()));
    }
}