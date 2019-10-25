package values.Mathematics;

import java.lang.Math;
import values.*;

public class Root extends Mathematic {
    private Value[] args;

    public Root (Value[] args) throws Exception {
        this.args = args;
    }

    public String getOriginal () {
        return "Root";
    }

    public String executeMath() throws Exception {
        return Double.toString(Math.round(Math.pow(Double.parseDouble(this.args[0].getValue()), 1.0/Double.parseDouble(this.args[1].getValue()))));
    }
}
