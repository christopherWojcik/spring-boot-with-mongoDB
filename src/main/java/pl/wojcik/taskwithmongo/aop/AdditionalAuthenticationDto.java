package pl.wojcik.taskwithmongo.aop;

public class AdditionalAuthenticationDto implements AdditionalCredentials{

    private String username;
    private String password;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
