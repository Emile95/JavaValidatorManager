import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import validatorManager.ValidatorManager;
import validatorManager.ValidatorProfile;

public class UseObjectTest {

    ValidatorManager validatorManager;

    public UseObjectTest() {
        validatorManager = new ValidatorManager(config -> {
            config.addProfile(new Profile());
        });
    }

    @Test                                              
    @DisplayName("Catch Excpected Validation Exception")   
    void catchValidationException() throws Exception{
        try { validatorManager.validate(new User("12345","1234567")); } 
        catch(ValidationException e) {} 
        try { validatorManager.validate(new User("1234","12345678")); } 
        catch(ValidationException e) {} 
    }
    
    @Test                                             
    @DisplayName("Skip value validation")   
    void SkipValueException() throws Exception {      
        validatorManager.validate(new User("12345","skipPasswordPlease"));
        validatorManager.validate(new User("skipPseudoPlease","12345678"));
    }

    @Test                                      
    @DisplayName("Skip object validation")   
    void SkipobjectException() throws Exception {
        validatorManager.validate(new User("sudo","1234567"));
    }
}

