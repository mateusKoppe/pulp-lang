package values.Mathematics;

import java.util.Arrays;
import java.util.List;

import values.Value;

abstract public class Mathematic extends Value {
    public static List<String> reservatedWords = Arrays.asList("sum","subtraction","sub","multiplication","mult","division","div","mod","pow","sqrt");
    
    abstract public String getOriginal();
    abstract public String executeMath() throws Exception;
    private String errorMath(){
        return "Invalid value in "+ getOriginal() +" operation, \nThis class cannot accept String or Negative numbers as parameters";
    }
    
    public String getValue() throws Exception {
        try{
            return executeMath();
        }catch(Exception e){
            return errorMath();
        }
    }
}