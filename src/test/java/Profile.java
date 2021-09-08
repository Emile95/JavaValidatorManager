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
                        )
                        .forValidation(
                            o -> o.length() <= 20,
                            (o) -> new PasswordMaximumLengthException(o,20)
                        );
                }      
            )
            .forValue(
                data -> data.pseudo, 
                value -> {
                    value
                        .forValidation(
                            o -> o.length() >= 2, 
                            (o) -> new PasswordMinimumLengthException(o,2)
                        );
                }
            );
    }
}