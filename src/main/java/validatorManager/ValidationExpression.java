package validatorManager;

import java.util.function.Function;

import validatorManager.interfaces.ValidatorContextConsumer;

class ValidationExpression<T,E extends Exception> extends Validator {

    ValidatorContextConsumer<T,Boolean> validationExpression;
    ValidatorContextConsumer<T,E> exceptionExpression;

    ValidationExpression(ValidatorContextConsumer<T,Boolean> validationExpression, ValidatorContextConsumer<T,E> exceptionExpression) {
        this.validationExpression = validationExpression;
        this.exceptionExpression = exceptionExpression;
    }

    ValidationResult validate(Object data, ValidatorContext context, ValidationResult result) throws Exception {
        T t = (T)data;
        if(!validationExpression.apply(t,context))
            throw exceptionExpression.apply(t,context);
        return result;
    }
}
