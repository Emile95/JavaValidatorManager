package validatorManager;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import validatorManager.interfaces.TwoParamConsumer;

class ValidatorExpression<T> extends Validator {

    TwoParamConsumer<T,ValidatorContext> contextExpression;
    ArrayList<Validator> validators;
    ArrayList<Function<T,Boolean>> noValidationExpressions;

    ValidatorExpression(TwoParamConsumer<T,ValidatorContext> contextExpression, ArrayList<Validator> validators, ArrayList<Function<T,Boolean>> noValidationExpressions) {
        this.contextExpression = contextExpression;
        this.validators = validators;
        this.noValidationExpressions = noValidationExpressions;
    }

    void validate(Object data, ValidatorContext context) throws Exception {
        T t = (T)data;
        contextExpression.accept(t,context);
        for(Function<T,Boolean> noValidationExpression : noValidationExpressions)
            if(noValidationExpression.apply(t)) return;
        for(Validator validator : validators)  
            validator.validate(t, context);
    }
}
