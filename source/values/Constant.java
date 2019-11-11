package values;

public class Constant extends Value {
    private String value;

    public Constant (String value) {
        this.value = value;
    }

    public String getOriginal () {
        return this.value;
    }

    public String getValue (Scope scope) {
        return this.value;
    }
}