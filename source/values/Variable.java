package values;

public class Variable extends Value {
    private String value;
    private Scope scope;

    public Variable (String value, Scope scope) {
        this.value = value;
        this.scope = scope;
    }

    public String getOriginal () {
        return this.value;
    }

    public String getValue () {
        return this.scope.getVariable(this.value);
    }

}