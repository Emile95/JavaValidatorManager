import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import validatorManager.exception.*;
import validatorManager.*;

public class CreateObjectTest {

    @Test                                               
    @DisplayName("Catch Duplicate Type Exception")   
    void CatchDuplicateTypeException() throws Exception {
        try {
            ValidatorManager validatorManager = new ValidatorManager(config -> {
                config.addProfile(new Profile());
                config.addProfile(new Profile());
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
            validatorManager.validate(new Integer(5));
        } catch(NotMappedTypeException e) {} 
    }

}
