package values.Conditions;

import values.*;

public class Greater extends Condition {
    private Value[] args;
    
    public Greater (Value[] args) throws Exception {
        this.args = args;
    }

    public String getOriginal () {
        return "equals";
    }

    public String executeCondition(Scope scope) throws Exception {
        Double param1 = Double.parseDouble(this.args[0].getValue(scope));
        Double param2 = Double.parseDouble(this.args[1].getValue(scope));
        Boolean truth = param1 > param2;
        return truth ? "1" : "0";
    }
}