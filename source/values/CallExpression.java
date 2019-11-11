package values;

import java.util.Arrays;

import operations.Function;
import operations.OperationResult;
import services.LexerExpression;

public class CallExpression extends Value {
    private String value;
    private Function function;
    private Value[] values;

    public CallExpression (String args[]) throws Exception {
        this.function = Function.getFunction(args[1]);
        String[] params = Arrays.copyOfRange(args, 2, args.length -1);
        this.values = LexerExpression.getExpressions(params);
    }

    public String getOriginal () {
        return this.value;
    }

    public String getValue (Scope scope) throws Exception {
        OperationResult result = this.function.call(this.values, scope);
        return result.value.getValue(scope);
    }

}