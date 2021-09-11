package validatorManager;

public abstract class Validator {
    abstract ValidationResult validate(Object data, ValidatorContext context, ValidationResult result) throws Exception;
}
