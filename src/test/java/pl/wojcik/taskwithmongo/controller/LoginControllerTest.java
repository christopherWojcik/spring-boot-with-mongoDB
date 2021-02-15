package pl.wojcik.taskwithmongo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import pl.wojcik.taskwithmongo.security.LoginCredentials;
import pl.wojcik.taskwithmongo.service.TestConstants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoginControllerTest extends AbstractSpringTest {

    @Test
    void shouldReturnStatusOkWhenLoginCredentialsAreValid() throws Exception {
        final LoginCredentials credentials = new LoginCredentials();
        credentials.setUsername(TestConstants.GOOD_LOGIN_USERNAME);
        credentials.setPassword(TestConstants.GOOD_LOGIN_PASSWORD);

        final MvcResult mvcResult = mockMvc.perform(
                post(TestConstants.LOGIN_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(credentials)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void shouldReturnStatus401WhenLoginCredentialsAreInvalid() throws Exception {
        final LoginCredentials wrongCredentials = new LoginCredentials();
        wrongCredentials.setUsername("test 3");
        wrongCredentials.setPassword("test 123");

        final MvcResult mvcResult = mockMvc.perform(
                post(TestConstants.LOGIN_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wrongCredentials)))
                .andExpect(status().isUnauthorized())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
}