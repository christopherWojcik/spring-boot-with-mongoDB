package pl.wojcik.taskwithmongo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AdditionalAuthenticationAspect {

    @Value("${secret.method.username}")
    private String secretMethodUsername;
    @Value("${secret.method.password}")
    private String secretMethodPassword;

    @Before("@annotation(authentication) && args(credentials,..)")
    public void before(AdditionalAuthentication authentication, AdditionalCredentials credentials){
        log.info("ASPECT :: AdditionalAuthenticationAspect :: ");

        // Here should be normal verification of user in e.g. database.
        //
        if (!(credentials.getUsername().equals(secretMethodUsername) &&
                credentials.getPassword().equals(secretMethodPassword))){
            throw new RuntimeException("Wrong credentials!");
        }
    }
}
