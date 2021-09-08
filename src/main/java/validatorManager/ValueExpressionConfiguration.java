package validatorManager;

import java.util.ArrayList;
import java.util.function.Function;

import validatorManager.interfaces.ValidatorConfiguration;
import validatorManager.interfaces.Validator;

public class ValueExpressionConfiguration<T,S> implements ValidatorConfiguration {
    
    Function<T,S> valueGetterExpression;
    ArrayList<Validator> validators;
    ArrayList<Function<S,Boolean>> noValidations;

    ValueExpressionConfiguration(Function<T,S> valueGetterExpression) {
        this.valueGetterExpression = valueGetterExpression;
        validators = new ArrayList<Validator>();
        noValidations = new ArrayList<Function<S,Boolean>>();
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

    /**
     * Add skip validation for your value
     * @param noValidationExpression expression who return boolean with the value as parameter
    */
    public ValueExpressionConfiguration<T,S> forNoValidation(Function<S,Boolean> noValidationExpression) {
        noValidations.add(noValidationExpression);
        return this;
    }

    public Validator createValidator() {
        return new ValueExpression<T,S>(valueGetterExpression, validators, noValidations);
    }
}
