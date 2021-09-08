package validatorManager;

import java.util.ArrayList;


public class ValidatorManagerConfiguration {
    ArrayList<ValidatorProfile> profiles;

    ValidatorManagerConfiguration() {
        profiles = new ArrayList<ValidatorProfile>();
    }

    /**
     * Add a profile to your ValidatorManager
     * @param profile New profile to add
    */
    public void addProfile(ValidatorProfile profile) {
        profiles.add(profile);
    }
}
