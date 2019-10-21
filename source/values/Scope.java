package values;

import java.util.HashMap;

import operations.Function;

public class Scope {
    private Scope parent;
    private HashMap<String, String> poolValue;
    private HashMap<String, Function> poolFunction;

    public Scope () {
        this(null);
    }

    public Scope (Scope parent) {
        this.parent = parent;
        this.poolValue = new HashMap<String, String>();
        this.poolFunction = new HashMap<String, Function>();
    }

    public void declareFunction (String name, Function function) {
        if (this.poolFunction.get(name) != null) {
            System.out.println("Function already declared");
        }
        this.poolFunction.put(name, function);
    }

    public void declareVariable (String name, String value) {
        if (this.poolValue.get(name) != null) {
            System.out.println("Variable already declared");
        }
        this.setVariable(name, value);
    }

    public void setVariable (String name, String value) {
        this.poolValue.put(name, value);
    }

    public Function getFunction (String name) {
        return this.poolFunction.get(name);
    }

    public String getVariable (String name) {
        Scope actualScope = this;
        String value;

        do {
            value = actualScope.poolValue.get(name);
            actualScope = actualScope.getParent();
        } while (value == null && actualScope != null);

        return value;
    }

    public Scope getParent () {
        return this.parent;
    }
}