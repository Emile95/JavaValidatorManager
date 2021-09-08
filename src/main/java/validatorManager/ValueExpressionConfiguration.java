package validatorManager;

import java.util.ArrayList;
import java.util.function.Function;

import validatorManager.interfaces.ValidatorConfiguration;
import validatorManager.interfaces.Validator;

public class ValueExpressionConfiguration<T,S> implements ValidatorConfiguration {
    
    Function<T,S> valueGetterExpression;
    ArrayList<Validator> validators;

    ValueExpressionConfiguration(Function<T,S> valueGetterExpression) {
        this.valueGetterExpression = valueGetterExpression;
        validators = new ArrayList<Validator>();
    }

    /**
     * Add validation for your value
     * @param validationExpression expression who return boolean with the value as parameter
     * @param exceptionExpression expression who create the exception if the validation expression is false
    */
    public <E extends Exception> ValueExpressionConfiguration<T,S> forValidation(Function<S,Boolean> validationExpression, Function<S,E> exceptionExpression) {
        validators.add(new ValidationExpression<S,E>(validationExpression, exceptionExpression));
        return this;
    }

    public Validator createValidator() {
        return new ValueExpression<T,S>(valueGetterExpression, validators);
    }
}
