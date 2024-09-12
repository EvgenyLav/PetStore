package assertions.conditions;

public class Conditions {
    public static MessageCondition hasMessage(String expectedMessage){
        return new MessageCondition(expectedMessage);
    }

    public static StatusCodeCondition hasStatusCode(Integer expectedStatus){
        return new StatusCodeCondition(expectedStatus);

    }

    public static TypeCondition hasType(String expectedType){
        return new TypeCondition(expectedType);
    }


    public static CodeCondition hasCode(Integer expectedCode){
        return new CodeCondition(expectedCode);
    }

}
