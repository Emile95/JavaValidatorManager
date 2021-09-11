import validatorManager.ValidatorProfile;

class Profile extends ValidatorProfile {
    Profile() {
        createValidator(User.class)
            .forNoValidation(o -> o.pseudo.equals("sudo"))
            .forValue(
                data -> data.password,
                value -> { 
                    value
                        .forNoValidation(o -> o.equals("skipPasswordPlease"))
                        .forValidation(
                            o -> o.length() >= 8,
                            (o) -> new ValidationException()
                        )
                        .forValidation(
                            o -> o.length() <= 20,
                            (o) -> new ValidationException()
                        );
                }      
            )
            .forValue(
                data -> data.pseudo, 
                value -> {
                    value
                        .forNoValidation(o -> o.equals("skipPseudoPlease"))
                        .forValidation(
                            o -> o.length() >= 5, 
                            (o) -> new ValidationException()
                        );
                }
            );
    }
}