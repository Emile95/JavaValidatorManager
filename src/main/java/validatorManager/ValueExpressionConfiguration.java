package validatorManager;

import java.util.ArrayList;
import java.util.function.Function;

import validatorManager.interfaces.ValidatorContextConsumer;

public class ValueExpressionConfiguration<T,S> extends ValidatorConfiguration {
    
    ValidatorContextConsumer<T,S> valueGetterExpression;
    ArrayList<Validator> validators;
    ArrayList<ValidatorContextConsumer<S,Boolean>> noValidations;

    ValueExpressionConfiguration(ValidatorContextConsumer<T,S> valueGetterExpression) {
        this.valueGetterExpression = valueGetterExpression;
        validators = new ArrayList<Validator>();
        noValidations = new ArrayList<ValidatorContextConsumer<S,Boolean>>();
    }

    /**
     * Add validation for your value
     * @param validationExpression expression who return boolean with the value as parameter
     * @param exceptionExpression expression who create the exception if the validation expression is false
    */
    public <E extends Exception> ValueExpressionConfiguration<T,S> forValidation(ValidatorContextConsumer<S,Boolean> validationExpression, ValidatorContextConsumer<S,E> exceptionExpression) {
        validators.add(new ValidationExpression<S,E>(validationExpression, exceptionExpression));
        return this;
    }

    /**
     * Add skip validation for your value
     * @param noValidationExpression expression who return boolean with the value as parameter
    */
    public ValueExpressionConfiguration<T,S> forNoValidation(ValidatorContextConsumer<S,Boolean> noValidationExpression) {
        noValidations.add(noValidationExpression);
        return this;
    }

    Validator createValidator() {
        return new ValueExpression<T,S>(valueGetterExpression, validators, noValidations);
    }
}
