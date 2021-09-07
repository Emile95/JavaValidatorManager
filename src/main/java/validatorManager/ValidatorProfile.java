package validatorManager;

import java.util.ArrayList;

public class ValidatorProfile {
    ArrayList<ValidatorExpressionConfiguration<?>> expressionConfigurations;

    public ValidatorProfile() {
        expressionConfigurations = new ArrayList<ValidatorExpressionConfiguration<?>>();
    }

    protected <T> ValidatorExpressionConfiguration<T> createValidator(Class<T> c) {
        ValidatorExpressionConfiguration<T> exp = new ValidatorExpressionConfiguration<T>(c);
        expressionConfigurations.add(exp);
        return exp;
    }
}
