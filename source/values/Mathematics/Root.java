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

    public String getValue () {
        //double root = Math.round(Math.pow(Double.parseDouble(this.args[0].getValue()), 1.0 / Double.parseDouble(this.args[1].getValue())));
        //assertEquals(5, root, 0);
        return Double.toString(Math.round(Math.pow(Double.parseDouble(this.args[0].getValue()), 1.0 / Double.parseDouble(this.args[1].getValue()))));
    }
}