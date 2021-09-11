package validatorManager;

import java.util.HashMap;
import java.util.function.Consumer;

import validatorManager.exceptions.*;

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

    /**
     * Validate an object depending on his type
     * @param data object to validate
    */
    public <T> void validate(T data) throws Exception {
        Class<?> c = data.getClass();
        if(!validators.containsKey(c))
            throw new NotMappedTypeException(c);
        validators.get(c).validate(data, new ValidatorContext());
    }
}
