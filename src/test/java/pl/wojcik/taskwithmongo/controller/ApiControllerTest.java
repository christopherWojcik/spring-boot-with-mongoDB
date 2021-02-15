package pl.wojcik.taskwithmongo.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import pl.wojcik.taskwithmongo.model.dto.MessageDTO;
import pl.wojcik.taskwithmongo.model.message.Message;
import pl.wojcik.taskwithmongo.model.request.SendRequest;
import pl.wojcik.taskwithmongo.model.request.UiRequest;
import pl.wojcik.taskwithmongo.repository.MessageRepository;
import pl.wojcik.taskwithmongo.service.TestConstants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ApiControllerTest extends AbstractSpringTest {

    @Autowired
    private MessageRepository repository;

    @BeforeEach
    void init() {
        repository.deleteAll();
        repository.save(Message.builder()
                .email(TestConstants.EMAIL_1)
                .title(TestConstants.TITLE_1)
                .content(TestConstants.CONTENT_1)
                .magicNumber(TestConstants.MAGIC_NUMBER_1)
                .build());
        repository.save(Message.builder()
                .email(TestConstants.EMAIL_1)
                .title(TestConstants.TITLE_2)
                .content(TestConstants.CONTENT_2)
                .magicNumber(TestConstants.MAGIC_NUMBER_2)
                .build());
        repository.save(Message.builder()
                .email(TestConstants.EMAIL_2)
                .title(TestConstants.TITLE_3)
                .content(TestConstants.CONTENT_3)
                .magicNumber(TestConstants.MAGIC_NUMBER_1)
                .build());
    }

    @AfterEach
    void clearRepositoryAfterTest(){
        repository.deleteAll();
    }

    @Test
    void getAllMessagesByEmail() throws Exception {
        // similar like test below + for All tests counterpart: shouldNot... or shouldThrowException...
    }

    @Test
    void getMessagesContentWithMagicNumber() throws Exception {
        final SendRequest request = new SendRequest();
        request.setMagic_number(101);

        final String requestAsString = objectMapper.writeValueAsString(request);

        final MvcResult mvcResult = mockMvc.perform(
                post(TestConstants.SEND_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(requestAsString))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertThat(contentAsString).contains(request.getMagic_number().toString());
        assertThat(contentAsString).contains(TestConstants.EMAIL_1);
        assertThat(contentAsString).contains(TestConstants.EMAIL_2);
        assertThat(contentAsString).doesNotContain(TestConstants.EMAIL_3);
    }

    @Test
    void shouldCreateMessage() throws Exception {
        final UiRequest request = UiRequest.builder()
                .email(TestConstants.EMAIL_1)
                .title(TestConstants.TITLE_1)
                .content(TestConstants.CONTENT_1)
                .magic_number(TestConstants.MAGIC_NUMBER_1)
                .build();
        final String requestAsString = objectMapper.writeValueAsString(request);

        MvcResult mvcResult = mockMvc.perform(
                post(TestConstants.CREATE_MESSAGE_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(requestAsString))
                .andDo(log())
                .andExpect(status().isCreated())
                .andReturn();

        final String content = mvcResult.getResponse().getContentAsString();
        final MessageDTO message = objectMapper.readValue(content, MessageDTO.class);

        assertThat(message.getEmail()).isEqualTo(request.getEmail());
        assertThat(message.getTitle()).isEqualTo(request.getTitle());
        assertThat(message.getContent()).isEqualTo(request.getContent());
        assertThat(message.getMagicNumber()).isEqualTo(request.getMagic_number());
        assertThat(message.getDto_id()).isNotNull();
    }
}