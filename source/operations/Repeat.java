package operations;

import java.io.BufferedReader;

import values.Scope;
import values.Value;
import services.Lexer;

public class Repeat extends Operation{

    public Repeat(Value[] params, Scope scope, BufferedReader reader){
        System.out.println(params);
    }


    public OperationResult execute() throws Exception{
        return null;
    }
}