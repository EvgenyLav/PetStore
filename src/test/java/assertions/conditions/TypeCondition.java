package assertions.conditions;

import assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import models.ResponseInfo;
import org.junit.jupiter.api.Assertions;


@RequiredArgsConstructor
public class TypeCondition implements Condition {
    private final String expectedType;


    @Override
    public void check(ValidatableResponse response) {
        ResponseInfo info = response.extract().jsonPath().getObject("", ResponseInfo.class);
        Assertions.assertEquals(expectedType, info.getType());
    }
}
