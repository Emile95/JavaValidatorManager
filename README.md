# JavaValidatorManager

Java library for configuration of data validation process.

create your object and use it as a service for validate your data.
use class who inherit of ValidatorProfile to create validator based on a specific type


Example of profile and type you want to validate


------------


import validatorManager.ValidatorProfile;

//Class you want his object to be validated
class User {
    String pseudo;
    String password;

    User(String pseudo, String password) {
        id = 1;
        guid = 2;
        this.pseudo = pseudo;
        this.password = password;
    }
}

------------

class Profile extends ValidatorProfile {
    Profile() {
        //Create a Validator for the User object
        createValidator(User.class)
            //Data validator context consumer to add some value in the whole validator process
            .forContext((data,context) -> {
                //Add a value in the context with the name totalLength
                context.addValue("totalLength", data.pseudo.length() + data.password.length());
            })
            //Skip the object validation process if pseudo equals pseudo
            .forNoValidation((data) -> data.pseudo.equals("sudo"))
            .forValue(
                
                (data,context) -> {
                    //Add a value in the context with the name passwordLength
                    context.addValue("passwordLength", data.password.length()) ;
                    //Define data.password as the value you want to validate
                    return data.password; 
                },
                value -> { 
                    value
                        .forNoValidation(
                            (o,context) ->  { 
                                //Add a value in the context with the name passwordValueValidationStatus
                                context.addValue("passwordValueValidationStatus", "skipped");
                                //Skip the value validation process if password equals skipPasswordPlease
                                return o.equals("skipPasswordPlease");
                            }
                        )
                        .forValidation(
                            (o,context) ->  { 
                                //Add a value in the context with the name passwordMinimumLength
                                context.addValue("passwordMinimumLength", 8);
                                //Boolean expression for telling if its validate
                                return o.length() >= 8; 
                            },
                            //Bind the exception that would be throwed is this validation is false and is context to get some value
                            (o,context) -> new ValidationException("password minimum " + Integer.toString(context.<Integer>getValue("passwordMinimumLength")) + "characters : you have " + Integer.toString(context.<Integer>getValue("passwordLength")))
                        )
                        .forValidation(
                            (o,context) -> { 
                                //Add a value in the context with the name passwordMaximumLength
                                context.addValue("passwordMaximumLength", 20);
                                //Boolean expression for telling if its validate
                                return o.length() <= 20;
                            },
                            //Bind the exception that would be throwed is this validation is false and is context to get some value
                            (o,context) -> new ValidationException("password maximum " + Integer.toString(context.<Integer>getValue("passwordMaximumLength")) + "characters : you have " + Integer.toString(context.<Integer>getValue("passwordLength")))
                        );
                }      
            )
            .forValue(
                (data,context) -> {
                    //Add a value in the context with the name passwordMinimumLength
                    context.addValue("pseudoLength", data.pseudo.length()) ;
                    //Define data.pseudo as the value you want to validate
                    return data.pseudo; 
                }, 
                value -> {
                    value
                        .forNoValidation((o,context) ->  { 
                            //Add a value in the context with the name pseudoValueValidationStatus
                            context.addValue("pseudoValueValidationStatus", "skipped");
                            //Skip the value validation process if pseudo equals skipPseudoPlease
                            return o.equals("skipPseudoPlease");
                        })
                        .forValidation(
                            (o,context) -> { 
                                //Add a value in the context with the name pseudoMinimumLength
                                context.addValue("pseudoMinimumLength", 5);
                                //Boolean expression for telling if its validate
                                return o.length() >= 5;
                            }, 
                            //Bind the exception that would be throwed is this validation is false and is context to get some value
                            (o,context) -> new ValidationException("pseudo minimum " + Integer.toString(context.<Integer>getValue("pseudoMinimumLength")) + "characters : you have " + Integer.toString(context.<Integer>getValue("pseudoLength")))
                        );
                }
            );
    }
}


------------


Expample of use your profile in the creation of validatorManagerObject and validate a object

------------------

validatorManager = new ValidatorManager(config -> {
  config.addProfile(new Profile());
});

validatorManager.validate(new User("12345","1234567"));

------------------


