package pl.wojcik.taskwithmongo.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.wojcik.taskwithmongo.security.LoginCredentials;

/*
    Used for swagger purposes
 */
@RestController
@Api(value = "LOGIN Controller", protocols = "http")
public class LoginController {

    @ApiOperation(value = "Login to app.",
            httpMethod = "POST",
            notes = "Created only for Swagger usage. Correct credentials: \"test\" and \"test\"")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED!!!")
    })
    @PostMapping("/login")
    public void login(
            @ApiParam(required = true,
                    name = "JSON object with fields: \"username\" and \"password\"",
                    example = "{ \"username\" : \"test\", \"password\" : \"test\" }")
            @RequestBody LoginCredentials credentials) {
    }
}
