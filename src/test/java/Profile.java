import validatorManager.ValidatorProfile;

class Profile extends ValidatorProfile {
    Profile() {
        createValidator(User.class)
            .forValue(
                data -> data.password,
                value -> { 
                    value
                        .forValidation(
                            o -> o.length() >= 8,
                            (o) -> new PasswordMinimumLengthException(o,8)
                        );
                }      
            );
    }
}