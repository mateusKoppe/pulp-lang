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

    public void declareFunction (String name, Function function) throws Exception {
        if (this.poolFunction.get(name) != null) {
            throw new Exception("Runtime Error: Function \""+name+"\" already declared");
        }
        this.poolFunction.put(name, function);
    }

    public void declareVariable (String name, String value) throws Exception {
        if (this.poolValue.get(name) != null) {
            throw new Exception("Runtime Error: Variable \""+name+"\" already declared");
        }
        this.poolValue.put(name, value);
    }

    public void setVariable (String name, String value) throws Exception {
        if (this.poolValue.get(name) != null) {
            this.poolValue.put(name, value);
            return;
        }

        Scope fetchScope = this;
        Scope backupScope = this;
        String fetchValue;

        do {
            fetchValue = fetchScope.poolValue.get(name);
            backupScope = fetchScope;
            fetchScope = fetchScope.getParent();
        } while (fetchValue == null && fetchScope != null);

        if (fetchValue != null) {
            backupScope.poolValue.put(name, value);
            return;
        }

        throw new Exception("Runtime Error: Variable \""+name+"\" not declared");
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