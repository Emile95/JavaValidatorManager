package validatorManager.exception;

import java.lang.RuntimeException;

public class NotMappedTypeException  extends RuntimeException {
    public NotMappedTypeException(Class<?> c) {
        super("Not Mapped Class : " + c.getName());
    }
}
