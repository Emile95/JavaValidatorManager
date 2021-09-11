import validatorManager.ValidatorProfile;

class Profile extends ValidatorProfile {
    Profile() {
        createValidator(User.class)
            .forNoValidation(o -> o.pseudo.equals("sudo"))
            .forValue(
                (data,context) -> {
                    context.addValue("passwordLength", data.password.length()) ;
                    return data.password; 
                },
                value -> { 
                    value
                        .forNoValidation(
                            (o,context) ->  { 
                                context.addValue("passwordValueValidationStatus", "skipped");
                                return o.equals("skipPasswordPlease");
                            }
                        )
                        .forValidation(
                            (o,context) ->  { 
                                context.addValue("passwordMinimumLength", 8);
                                return o.length() >= 8; 
                            },
                            (o,context) -> new ValidationException("password minimum " + Integer.toString(context.<Integer>getValue("passwordMinimumLength")) + "characters : you have " + Integer.toString(context.<Integer>getValue("passwordLength")))
                        )
                        .forValidation(
                            (o,context) -> { 
                                context.addValue("passwordMaximumLength", 20);
                                return o.length() <= 20;
                            },
                            (o,context) -> new ValidationException("password maximum " + Integer.toString(context.<Integer>getValue("passwordMaximumLength")) + "characters : you have " + Integer.toString(context.<Integer>getValue("passwordLength")))
                        );
                }      
            )
            .forValue(
                (data,context) -> {
                    context.addValue("pseudoLength", data.pseudo.length()) ;
                    return data.pseudo; 
                }, 
                value -> {
                    value
                        .forNoValidation((o,context) ->  { 
                            context.addValue("pseudoValueValidationStatus", "skipped");
                            return o.equals("skipPseudoPlease");
                        })
                        .forValidation(
                            (o,context) -> { 
                                context.addValue("pseudoMinimumLength", 5);
                                return o.length() >= 5;
                            }, 
                            (o,context) -> new ValidationException("pseudo minimum " + Integer.toString(context.<Integer>getValue("pseudoMinimumLength")) + "characters : you have " + Integer.toString(context.<Integer>getValue("pseudoLength")))
                        );
                }
            );
    }
}