package validatorManager;

import java.util.HashMap;
import java.util.function.Consumer;

import validatorManager.exception.*;
import validatorManager.interfaces.Validator;

public class ValidatorManager {

    private HashMap<Class<?>,Validator> validators;
    
    public ValidatorManager(Consumer<ValidatorManagerConfiguration> consumer) {
        ValidatorManagerConfiguration config = new ValidatorManagerConfiguration();
        consumer.accept(config);
        validators = new HashMap<Class<?>,Validator>();
        for(ValidatorProfile profile : config.profiles) {
            for(ValidatorExpressionConfiguration<?> expressionConfiguration : profile.expressionConfigurations){
                if(validators.containsKey(expressionConfiguration.type))
                    throw new DuplicateTypeException(expressionConfiguration.type);
                    validators.put(expressionConfiguration.type, expressionConfiguration.createValidator());
            }
        }
    }

    public <T> void validate(T data) throws Exception {
        validators.get(data.getClass()).validate(data);
    }
}
