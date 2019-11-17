package values.Conditions;

import values.*;

public class Not extends Condition {
    private Value[] args;
    
    public Not (Value[] args) throws Exception {
        this.args = args;
    }

    public String getOriginal () {
        return "and";
    }

    public String executeCondition(Scope scope) throws Exception {
        Double param1 = Double.parseDouble(this.args[0].getValue(scope));
        if (param1 != 0) {
            return "0";
        }
        return "1";
    }
}