package values.Conditions;

import values.*;

public class And extends Condition {
    private Value[] args;
    
    public And (Value[] args) throws Exception {
        this.args = args;
    }

    public String getOriginal () {
        return "and";
    }

    public String executeCondition(Scope scope) throws Exception {
        Double param1 = Double.parseDouble(this.args[0].getValue(scope));
        Double param2 = Double.parseDouble(this.args[1].getValue(scope));
        if (param1 != 0 && param2 != 0) {
            return "1";
        }
        return "0";
    }
}