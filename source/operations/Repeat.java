package operations;

import java.io.BufferedReader;

import values.Scope;
import values.Value;

public class Repeat extends Operation{

    public Repeat(Value[] params, Scope scope, BufferedReader reader){
        System.out.println(params);
    }


    public OperationResult execute(Scope scope) throws Exception{
        return null;
    }
}