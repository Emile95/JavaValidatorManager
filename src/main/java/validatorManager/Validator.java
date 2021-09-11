package validatorManager;

public abstract class Validator {
    abstract void validate(Object data, ValidatorContext context) throws Exception;
}
