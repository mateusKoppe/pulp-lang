package values.Mathematics;

import java.util.Arrays;
import java.util.List;

import values.Scope;
import values.Value;

abstract public class Mathematic extends Value {
    public static List<String> reservedWords = Arrays.asList("sum","subtraction","sub","multiplication","mult","division","div","mod","pow","root");
    
    abstract public String getOriginal();
    abstract public String executeMath(Scope scope) throws Exception;
    private String errorMath(){
        return "Invalid value in "+ getOriginal() +" operation, \nThis class cannot accept String or Negative numbers as parameters";
    }
    
    public String getValue(Scope scope) throws Exception {
        try{
            return executeMath(scope);
        }catch(Exception e){
            throw new Exception(errorMath());
        }
    }
}