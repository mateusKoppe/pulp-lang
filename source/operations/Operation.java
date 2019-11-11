package operations;

import values.Scope;

abstract public class Operation {
    abstract public OperationResult execute(Scope scope) throws Exception;
}