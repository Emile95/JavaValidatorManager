import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import validatorManager.ValidationResult;
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
    void catchValidationException() throws Exception {
        try { validatorManager.validate(new User("12345","1234567")); } 
        catch(ValidationException e) {} 
        try { validatorManager.validate(new User("12345","1234567891233333333333333333333333333333333333333")); } 
        catch(ValidationException e) {} 
        try { validatorManager.validate(new User("1234","12345678")); } 
        catch(ValidationException e) {} 
    }
    
    @Test                                             
    @DisplayName("Skip value validation")   
    void skipValueException() throws Exception {      
        validatorManager.validate(new User("12345","skipPasswordPlease"));
        validatorManager.validate(new User("skipPseudoPlease","12345678"));
    }

    @Test                                      
    @DisplayName("Skip object validation")   
    void skipobjectException() throws Exception {
        validatorManager.validate(new User("sudo","1234567"));
    }

    @Test                                      
    @DisplayName("Check if expected Validator Context")   
    void checkIfExpectedValidatorContext() throws Exception {
        ValidationResult result = validatorManager.validate(new User("Fefeto","Pakipaki2"));
        assertEquals(6, result.<Integer>getContextValue("pseudoLength"));
        assertEquals(9, result.<Integer>getContextValue("passwordLength"));
        assertEquals(15, result.<Integer>getContextValue("totalLength"));
        assertEquals(8, result.<Integer>getContextValue("passwordMinimumLength"));
        assertEquals(20, result.<Integer>getContextValue("passwordMaximumLength"));
        assertEquals(5, result.<Integer>getContextValue("pseudoMinimumLength"));
    }
}

