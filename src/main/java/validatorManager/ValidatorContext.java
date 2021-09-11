package validatorManager;

import java.util.HashMap;

public class ValidatorContext {
    private HashMap<String, Object> values;

    ValidatorContext() {
        values = new HashMap<String, Object>();
    }

    public void addValue(String key, Object value) {
        values.put(key, value);
    }

    public <T> T getValue(String key) {
        return (T)(values.get(key));
    }
}
