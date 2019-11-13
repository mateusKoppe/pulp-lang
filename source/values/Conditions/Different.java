package values.Conditions;

import values.*;

public class Different extends Condition {
    private Value[] args;
    
    public Different (Value[] args) throws Exception {
        this.args = args;
    }

    public String getOriginal () {
        return "equals";
    }

    public String executeCondition(Scope scope) throws Exception {
        String param1 = this.args[0].getValue(scope);
        String param2 = this.args[1].getValue(scope);
        if (!param1.equals(param2)) {
            return "1";
        }
        return "0";
    }
}