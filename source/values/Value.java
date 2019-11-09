package values;

abstract public class Value {
    abstract public String getValue (Scope scope) throws Exception;
    abstract public String getOriginal ();
}