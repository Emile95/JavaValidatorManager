package validatorManager;

import java.util.ArrayList;
import java.util.function.Function;

import validatorManager.interfaces.Validator;

class ValueExpression<T,S> implements Validator {

    Function<T,S> valueGetterExpression;
    ArrayList<Validator> validators;

    public ValueExpression(Function<T,S> valueGetterExpression, ArrayList<Validator> validators) {
        this.valueGetterExpression = valueGetterExpression;
        this.validators = validators;
    }

    public void validate(Object data) throws Exception {
        S value = valueGetterExpression.apply((T)data);
        for(Validator validator : validators)
           validator.validate(value);
    }
}
