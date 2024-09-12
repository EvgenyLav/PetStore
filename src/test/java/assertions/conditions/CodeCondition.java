package assertions.conditions;


import assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import models.ResponseInfo;
import org.junit.jupiter.api.Assertions;

@RequiredArgsConstructor
public class CodeCondition implements Condition {
    private final Integer expectedCode;


    @Override
    public void check(ValidatableResponse response) {
        ResponseInfo info = response.extract().jsonPath().getObject("", ResponseInfo.class);
        Assertions.assertEquals(expectedCode, info.getCode());
    }
}
