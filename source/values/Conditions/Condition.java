package values.Conditions;

import java.util.Arrays;
import java.util.List;

import values.Scope;
import values.Value;

abstract public class Condition extends Value {
    public static List<String> reservedWords = Arrays.asList("and", "or", "equals", "different", "diff", "greater", "greaterequals", "less", "lessequals", "not");
    
    abstract public String executeCondition(Scope scope) throws Exception;

    private String errorMath(){
        return "Invalid value in "+ getOriginal() +" operation, \nThis class cannot accept String or Negative numbers as parameters";
    }
    
    public String getValue(Scope scope) throws Exception {
        try{
            return executeCondition(scope);
        }catch(Exception e){
            throw new Exception(errorMath());
        }
    }
}