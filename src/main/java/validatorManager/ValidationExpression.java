package validatorManager;

import java.util.function.Function;

class ValidationExpression<T,E extends Exception> extends Validator {

    Function<T,Boolean> validationExpression;
    Function<T,E> exceptionExpression;

    ValidationExpression(Function<T,Boolean> validationExpression, Function<T,E> exceptionExpression) {
        this.validationExpression = validationExpression;
        this.exceptionExpression = exceptionExpression;
    }

    void validate(Object data) throws Exception {
        T t = (T)data;
        if(!validationExpression.apply(t))
            throw exceptionExpression.apply(t);
    }
}
