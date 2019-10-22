package values;

import values.Value;

public class Sum extends Value {
    private Value[] args;

    public Sum (Value[] args) throws Exception {
        this.args = args;
    }

    public String getOriginal () {
        return "sum";
    }

    public String getValue () {
        return Double.toString(Double.parseDouble(this.args[0].getValue()) + Double.parseDouble(this.args[1].getValue()));
    }
}