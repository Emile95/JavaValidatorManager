package validatorManager;

import java.util.ArrayList;
import java.util.function.Function;

class ValidatorExpression<T> extends Validator {

    ArrayList<Validator> validators;
    ArrayList<Function<T,Boolean>> noValidationExpressions;

    ValidatorExpression(ArrayList<Validator> validators, ArrayList<Function<T,Boolean>> noValidationExpressions) {
        this.validators = validators;
        this.noValidationExpressions = noValidationExpressions;
    }

    void validate(Object data) throws Exception {
        for(Function<T,Boolean> noValidationExpression : noValidationExpressions)
            if(noValidationExpression.apply((T)data)) return;
        for(Validator validator : validators) 
            validator.validate(data);
    }
}
