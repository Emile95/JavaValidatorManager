import validatorManager.annotations.ContextValue;

class User {

    @ContextValue(key = "value")
    int id;

    @ContextValue
    int guid;

    String pseudo;
    String password;

    User(String pseudo, String password) {
        id = 0;
        this.pseudo = pseudo;
        this.password = password;
    }
}
