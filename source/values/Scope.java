package values;

import java.util.HashMap;

public class Scope {
    private Scope parent;
    private HashMap<String, String> pool;

    public Scope () {
        this(null);
    }

    public Scope (Scope parent) {
        this.parent = parent;
        this.pool = new HashMap<String, String>();
    }

    public void declareVariable (String name, String value) {
        if (this.pool.get(name) != null) {
            System.out.println("Variable already declared");
        }
        this.setVariable(name, value);
    }

    public void setVariable (String name, String value) {
        this.pool.put(name, value);
    }

    public String getVariable (String name) {
        return this.pool.get(name);
    }

    public Scope getParent () {
        return this.parent;
    }
}