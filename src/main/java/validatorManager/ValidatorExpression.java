package validatorManager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import validatorManager.annotations.ContextValue;
import validatorManager.interfaces.TwoParamConsumer;

class ValidatorExpression<T> extends Validator {

    TwoParamConsumer<T,ValidatorContext> contextExpression;
    ArrayList<Validator> validators;
    ArrayList<Function<T,Boolean>> noValidationExpressions;
    Class<T> c;

    ValidatorExpression(TwoParamConsumer<T,ValidatorContext> contextExpression, ArrayList<Validator> validators, ArrayList<Function<T,Boolean>> noValidationExpressions, Class<T> c) {
        this.contextExpression = contextExpression;
        this.validators = validators;
        this.noValidationExpressions = noValidationExpressions;
        this.c = c;
    }

    private void checkContextValueAnnotation(Field field, Object obj, ValidatorContext context) {
        if(!field.isAnnotationPresent(ContextValue.class)) return;
        ContextValue contextValue = field.getAnnotation(ContextValue.class);
        context.addValue(contextValue.equals("") ? field.getName() : contextValue.key(), obj);
    }

    ValidationResult validate(Object data, ValidatorContext context, ValidationResult result) throws Exception {
        T t = (T)data;

        for(Field field : c.getDeclaredFields()) {
            checkContextValueAnnotation(field, data, context);
        }

        if(contextExpression != null) contextExpression.accept(t,context);

        for(Function<T,Boolean> noValidationExpression : noValidationExpressions)
            if(noValidationExpression.apply(t)) return result;

        for(Validator validator : validators)  
            validator.validate(t, context, result);

        return result;
    }
}
