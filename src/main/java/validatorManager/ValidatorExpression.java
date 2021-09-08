package validatorManager;

import java.util.ArrayList;
import java.util.function.Function;

import validatorManager.interfaces.Validator;

class ValidatorExpression<T> implements Validator {

    ArrayList<Validator> validators;
    ArrayList<Function<T,Boolean>> noValidationExpressions;

    ValidatorExpression(ArrayList<Validator> validators, ArrayList<Function<T,Boolean>> noValidationExpressions) {
        this.validators = validators;
        this.noValidationExpressions = noValidationExpressions;
    }

    public void validate(Object data) throws Exception {
        for(Function<T,Boolean> noValidationExpression : noValidationExpressions)
            if(noValidationExpression.apply((T)data)) return;
        for(Validator validator : validators) 
            validator.validate(data);
    }
}
