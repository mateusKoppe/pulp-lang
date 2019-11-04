package values;

public class FunctionReturn extends Value {
    private Value[] args;
    
    public FunctionReturn (Value[] args) throws Exception {
        this.args = args;
    }
    
    public String getOriginal() {
        return null;
    }

    public String getValue() throws Exception {
        System.out.printf(this.args[0].getValue());
        return null;
    }
}