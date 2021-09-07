package validatorManager;

import java.util.ArrayList;


public class ValidatorManagerConfiguration {
    ArrayList<ValidatorProfile> profiles;

    ValidatorManagerConfiguration() {
        profiles = new ArrayList<ValidatorProfile>();
    }

    public void addProfile(ValidatorProfile profile) {
        profiles.add(profile);
    }
}
