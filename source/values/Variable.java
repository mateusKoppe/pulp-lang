package values;

public class Variable extends Value {
    private String value;

    public Variable (String value) {
        this.value = value;
    }

    public String getOriginal () {
        return this.value;
    }

    public String getValue (Scope scope) {
        return scope.getVariable(this.value);
    }

}