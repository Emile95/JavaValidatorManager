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

class ProfileSkipValueValidation extends ValidatorProfile {
    ProfileSkipValueValidation() {
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

class ProfileSkipObjectValidation extends ValidatorProfile {
    ProfileSkipObjectValidation() {
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
    @DisplayName("Catch Validation Exception")   
    void catchValidationException() throws Exception{
        ValidatorManager validatorManager = new ValidatorManager(config -> {
            config.addProfile(new Profile());
        });
        try {
            validatorManager.validate(new User("Fefeto","orion"));
        } catch(PasswordMinimumLengthException e) {} 
    }

    @Test                                               
    @DisplayName("Skip value validation")   
    void SkipValueException() throws Exception {
        ValidatorManager validatorManager = new ValidatorManager(config -> {
            config.addProfile(new ProfileSkipValueValidation());
        });
        validatorManager.validate(new User("Fefeto","skipValueValidationPlease"));
    }

    @Test                                               
    @DisplayName("Skip object validation")   
    void SkipobjectException() throws Exception {
        ValidatorManager validatorManager = new ValidatorManager(config -> {
            config.addProfile(new ProfileSkipObjectValidation());
        });
        validatorManager.validate(new User("sudo","orion"));
    }
}

