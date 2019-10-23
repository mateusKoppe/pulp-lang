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

    public String getValue () {
        return Double.toString( Math.pow(Double.parseDouble(this.args[0].getValue()), Double.parseDouble(this.args[1].getValue())));
    }
}