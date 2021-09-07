package validatorManager;

import java.util.function.Function;
import validatorManager.interfaces.Validator;

class ValidationExpression<T,E extends Exception> implements Validator {

    Function<T,Boolean> validationExpression;
    Function<T,E> exceptionExpression;

    ValidationExpression(Function<T,Boolean> validationExpression, Function<T,E> exceptionExpression) {
        this.validationExpression = validationExpression;
        this.exceptionExpression = exceptionExpression;
    }

    public void validate(Object data) throws Exception {
        T t = (T)data;
        if(!validationExpression.apply(t))
            throw exceptionExpression.apply(t);
    }
}
