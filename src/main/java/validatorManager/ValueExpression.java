package validatorManager;

import java.util.ArrayList;
import java.util.function.Function;

class ValueExpression<T,S> extends Validator {

    Function<T,S> valueGetterExpression;
    ArrayList<Validator> validators;
    ArrayList<Function<S,Boolean>> noValidations;

    public ValueExpression(Function<T,S> valueGetterExpression, ArrayList<Validator> validators, ArrayList<Function<S,Boolean>> noValidations) {
        this.valueGetterExpression = valueGetterExpression;
        this.validators = validators;
        this.noValidations = noValidations;
    }

    void validate(Object data) throws Exception {
        S value = valueGetterExpression.apply((T)data);
        for(Function<S,Boolean> noValidation: noValidations) 
            if(noValidation.apply(value)) return;
        for(Validator validator : validators)
           validator.validate(value);
    }
}
