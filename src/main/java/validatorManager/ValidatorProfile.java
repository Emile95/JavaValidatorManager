package validatorManager;

import java.util.ArrayList;

public class ValidatorProfile {
    ArrayList<ValidatorExpressionConfiguration<?>> expressionConfigurations;

    public ValidatorProfile() {
        expressionConfigurations = new ArrayList<ValidatorExpressionConfiguration<?>>();
    }

    /**
     * Add a new validator
     * @param c Class of your valiadator
    */
    protected <T> ValidatorExpressionConfiguration<T> createValidator(Class<T> c) {
        ValidatorExpressionConfiguration<T> exp = new ValidatorExpressionConfiguration<T>(c);
        expressionConfigurations.add(exp);
        return exp;
    }
}
