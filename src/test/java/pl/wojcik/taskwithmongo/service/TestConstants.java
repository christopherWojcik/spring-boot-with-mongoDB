package pl.wojcik.taskwithmongo.service;

public interface TestConstants {
    String EMAIL_1 = "jan.kowalski@example.com";
    String EMAIL_2 = "anna.zajkowska@example.com";
    String EMAIL_3 = "kris.krisowky@example.com";

    String TITLE_1 = "Interview";
    String TITLE_2 = "Interview 2";
    String TITLE_3 = "Interview 3";

    String CONTENT_1 = "simple text";
    String CONTENT_2 = "simple text 2";
    String CONTENT_3 = "simple text 3";

    Integer MAGIC_NUMBER_1 = 101;
    Integer MAGIC_NUMBER_2 = 22;
    Integer MAGIC_NUMBER_3 = 101;

    String LOGIN_PATH = "/login";
    String CREATE_MESSAGE_PATH = "/api/message";
    String SEND_PATH = "/api/send";
    String GOOD_LOGIN_USERNAME = "test";
    String GOOD_LOGIN_PASSWORD = "test";

    String REQUEST_AUTHORIZATION_TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNjEzMzg5NzE4fQ.CYXsOJlJPlTh425m0kg9Ewd-Ih6mFmv_FGCCPxCW51k";

    String LOGIN_CREDENTIALS_EXAMPLE_TO_STRING = "{ \"username\" : \"test\", \"password\" : \"test\" }";
}
