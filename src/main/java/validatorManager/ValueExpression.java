package validatorManager;

import java.util.ArrayList;
import java.util.function.Function;

import validatorManager.interfaces.ValidatorContextConsumer;

class ValueExpression<T,S> extends Validator {

    ValidatorContextConsumer<T,S> valueGetterExpression;
    ArrayList<Validator> validators;
    ArrayList<ValidatorContextConsumer<S,Boolean>> noValidations;

    public ValueExpression(ValidatorContextConsumer<T,S> valueGetterExpression, ArrayList<Validator> validators, ArrayList<ValidatorContextConsumer<S,Boolean>> noValidations) {
        this.valueGetterExpression = valueGetterExpression;
        this.validators = validators;
        this.noValidations = noValidations;
    }

    ValidationResult validate(Object data, ValidatorContext context, ValidationResult result) throws Exception {
        S value = valueGetterExpression.apply((T)data,context);
        for(ValidatorContextConsumer<S,Boolean> noValidation: noValidations) 
            if(noValidation.apply(value,context)) return result;
        for(Validator validator : validators)
           validator.validate(value, context, result);
        return result;
    }
}
