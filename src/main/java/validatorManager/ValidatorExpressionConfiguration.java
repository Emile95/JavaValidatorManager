package validatorManager;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

import validatorManager.interfaces.ValidatorContextConsumer;

public class ValidatorExpressionConfiguration<T> extends ValidatorConfiguration {

    ArrayList<ValidatorConfiguration> valueConfigs;
    ArrayList<Function<T,Boolean>> noValidationExpressions;
    Class<T> type;

    ValidatorExpressionConfiguration(Class<T> type) {
        this.type = type;
        valueConfigs = new ArrayList<ValidatorConfiguration>();
        noValidationExpressions = new ArrayList<Function<T,Boolean>>();
    }

    /**
     * Add a value to validate from your object
     * @param valueGetterExpression expression to get the value from your object
     * @param config consumer for configuring your value validation
    */
    public <S> ValidatorExpressionConfiguration<T> forValue(ValidatorContextConsumer<T,S> valueGetterExpression ,Consumer<ValueExpressionConfiguration<T,S>> config) {
        ValueExpressionConfiguration<T,S> valueConfig = new ValueExpressionConfiguration<T,S>(valueGetterExpression);
        config.accept(valueConfig);
        valueConfigs.add(valueConfig);
        return this;
    }

    /**
     * Add skip validation for your object
     * @param noValidationExpression expression who return boolean with the object as parameter
    */
    public ValidatorExpressionConfiguration<T> forNoValidation(Function<T,Boolean> noValidationExpression) {
        noValidationExpressions.add(noValidationExpression);
        return this;
    }

    Validator createValidator() {
        ArrayList<Validator> validators = new ArrayList<Validator>();
        for(ValidatorConfiguration config : valueConfigs) 
            validators.add((Validator)(config.createValidator()));
        return new ValidatorExpression<T>(validators,noValidationExpressions);
    }
}
