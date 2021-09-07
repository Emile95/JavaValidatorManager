package validatorManager;

import java.util.ArrayList;
import java.util.function.Function;

import validatorManager.interfaces.Validator;

class ValidatorExpression<T> implements Validator {

    ArrayList<Validator> validators;

    ValidatorExpression(ArrayList<Validator> validators) {
        this.validators = validators;
    }

    public void validate(Object data) throws Exception {
        for(Validator validator : validators) 
            validator.validate(data);
    }
}
