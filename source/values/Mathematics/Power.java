package values.Mathematics;

import java.lang.Math;
import values.*;

public class Power extends Mathematic {
    private Value[] args;

    public Power (Value[] args) throws Exception {
        this.args = args;
    }

    public String getOriginal () {
        return "Power";
    }

    public String executeMath(Scope scope) throws Exception {
        return Double.toString(Math.pow(Double.parseDouble(this.args[0].getValue(scope)), Double.parseDouble(this.args[1].getValue(scope))));
    }
}