package validatorManager;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

import validatorManager.interfaces.ValidatorConfiguration;
import validatorManager.interfaces.Validator;

public class ValidatorExpressionConfiguration<T> implements ValidatorConfiguration {

    ArrayList<ValidatorConfiguration> valueConfigs;
    Class<T> type;

    ValidatorExpressionConfiguration(Class<T> type) {
        this.type = type;
        valueConfigs = new ArrayList<ValidatorConfiguration>();
    }

    public <S> ValidatorExpressionConfiguration<T> forValue(Function<T,S> valueGetterExpression ,Consumer<ValueExpressionConfiguration<T,S>> config) {
        ValueExpressionConfiguration<T,S> valueConfig = new ValueExpressionConfiguration<T,S>(valueGetterExpression);
        config.accept(valueConfig);
        valueConfigs.add(valueConfig);
        return this;
    }

    public Validator createValidator() {
        ArrayList<Validator> validators = new ArrayList<Validator>();
        for(ValidatorConfiguration config : valueConfigs) 
            validators.add((Validator)(config.createValidator()));
        return new ValidatorExpression<T>(validators);
    }
}
