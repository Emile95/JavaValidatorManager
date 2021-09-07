import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import validatorManager.ValidatorManager;
import validatorManager.ValidatorProfile;

class Data {
    String name;
    String password;

    Data(String name, String password) {
        this.name = name;
        this.password = password;
    }
}

class Profile extends ValidatorProfile {
    Profile() {
        createValidator(Data.class)
            .forValue(
                data -> data.name,
                value -> { 
                    value
                        .forValidation(
                            o -> !o.equals("hohoho"),
                            (o) -> new Exception("Not Hohoh")
                        )
                        .forValidation(
                            o -> !o.equals("hahaha"),
                            (o) -> new Exception("Not hahaha")
                        );
                }      
            );
    }
}

public class TestCase1 {
    @Test                                               
    @DisplayName("Test 1")   
    void test1() throws Exception {
        ValidatorManager validatorManager = new ValidatorManager(config -> {
            config.addProfile(new Profile());
        });
        validatorManager.validate(new Data("dfsfsdfsdf","asdasdasd"));
    }
}

