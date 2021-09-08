import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import validatorManager.ValidatorManager;
import validatorManager.ValidatorProfile;

import validatorManager.exception.*;

class ProfileDuplicate extends ValidatorProfile {
    ProfileDuplicate() {
        createValidator(User.class);
    }
}
class NotMapped {}

public class CatchExceptionTest {

    @Test                                               
    @DisplayName("Catch Duplicate Type Exception")   
    void CatchDuplicateTypeException() throws Exception {
        try {
            ValidatorManager validatorManager = new ValidatorManager(config -> {
                config.addProfile(new Profile());
                config.addProfile(new ProfileDuplicate());
            });
        } catch(DuplicateTypeException e) {} 
    }

    @Test                                               
    @DisplayName("Catch Not Mapped Type Exception")   
    void CatchNotMappedTypeException() throws Exception {
        try {
            ValidatorManager validatorManager = new ValidatorManager(config -> {
                config.addProfile(new Profile());
            });
            validatorManager.validate(new NotMapped());
        } catch(NotMappedTypeException e) {} 
    }
    
}
