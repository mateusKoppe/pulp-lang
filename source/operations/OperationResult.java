package operations;

import values.Value;

public class OperationResult {
    public String type;
    public Value value;
    
    public OperationResult (String type) {
        this.type = type;
    }
}