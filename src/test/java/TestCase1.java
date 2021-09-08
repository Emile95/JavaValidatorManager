import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import validatorManager.ValidatorManager;
import validatorManager.ValidatorProfile;

class User {
    String pseudo;
    String password;

    User(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }
}

class PasswordMinimumLengthException extends Exception {
    PasswordMinimumLengthException(String password, int minimum) {
        super("Minimum " + Integer.toString(minimum) + " characters : you provide " + password.length() + " characters");
    }
}

class Profile extends ValidatorProfile {
    Profile() {
        createValidator(User.class)
            .forValue(
                data -> data.password,
                value -> { 
                    value
                        .forValidation(
                            o -> o.length() >= 8,
                            (o) -> new PasswordMinimumLengthException(o,8)
                        );
                }      
            );
    }
}

class Profile2 extends ValidatorProfile {
    Profile2() {
        createValidator(User.class)
            .forValue(
                data -> data.password,
                value -> { 
                    value
                        .forNoValidation(o -> !o.equals("skipValueValidationPlease"))
                        .forValidation(
                            o -> o.length() >= 8,
                            (o) -> new PasswordMinimumLengthException(o,8)
                        );
                }      
            );
    }
}

class Profile3 extends ValidatorProfile {
    Profile3() {
        createValidator(User.class)
            .forNoValidation(data -> data.pseudo.equals("sudo"))
            .forValue(
                data -> data.password,
                value -> { 
                    value
                        .forValidation(
                            o -> o.length() >= 8,
                            (o) -> new PasswordMinimumLengthException(o,8)
                        );
                }      
            );
    }
}

public class TestCase1 {
    @Test                                              
    @DisplayName("Test 1")   
    void test1() throws Exception{
        ValidatorManager validatorManager = new ValidatorManager(config -> {
            config.addProfile(new Profile());
        });
        try {
            validatorManager.validate(new User("Fefeto","orion"));
        } catch(PasswordMinimumLengthException e) {} 
    }

    @Test                                               
    @DisplayName("Test 2")   
    void test2() throws Exception {
        ValidatorManager validatorManager = new ValidatorManager(config -> {
            config.addProfile(new Profile2());
        });
        validatorManager.validate(new User("Fefeto","skipValueValidationPlease"));
    }

    @Test                                               
    @DisplayName("Test 3")   
    void test3() throws Exception {
        ValidatorManager validatorManager = new ValidatorManager(config -> {
            config.addProfile(new Profile3());
        });
        validatorManager.validate(new User("sudo","orion"));
    }
}

