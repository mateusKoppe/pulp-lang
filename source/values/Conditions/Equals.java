package values.Conditions;

import values.*;

public class Equals extends Condition {
    private Value[] args;
    
    public Equals (Value[] args) throws Exception {
        this.args = args;
    }

    public String getOriginal () {
        return "equals";
    }

    public String executeCondition(Scope scope) throws Exception {
        String param1 = this.args[0].getValue(scope);
        String param2 = this.args[1].getValue(scope);
        if (param1.equals(param2)) {
            return "1";
        }
        try {
            Double expression1 = Double.parseDouble(param1);
            Double expression2 = Double.parseDouble(param2);
            param1 = expression1.toString();
            param2 = expression2.toString();
            if (param1.equals(param2)) {
                return "1";
            }
        } catch (Exception e) {}
        return "0";
    }
}