package validatorManager.interfaces;

import validatorManager.ValidatorContext;

public interface ValidatorContextConsumer<ParameterType,ReturnType> {
    public ReturnType apply(ParameterType obj, ValidatorContext context);
}
