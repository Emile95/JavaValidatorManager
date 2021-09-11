package validatorManager;

public class ValidationResult {

    ValidatorContext context;

    ValidationResult(ValidatorContext context) {
        this.context = context;
    }

    public <T> T getContextValue(String key) {
        return context.<T>getValue(key);
    }
}
