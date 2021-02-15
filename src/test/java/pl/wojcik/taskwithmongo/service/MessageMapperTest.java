package pl.wojcik.taskwithmongo.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wojcik.taskwithmongo.controller.AbstractSpringTest;
import pl.wojcik.taskwithmongo.model.dto.MessageDTO;
import pl.wojcik.taskwithmongo.model.message.Message;
import pl.wojcik.taskwithmongo.model.request.UiRequest;
import pl.wojcik.taskwithmongo.repository.MessageRepository;

import static org.assertj.core.api.Assertions.assertThat;

class MessageMapperTest extends AbstractSpringTest {

    @Autowired
    private MessageRepository repository;

    @BeforeEach
    void clearRepo(){
        repository.deleteAll();
    }

    @AfterEach
    void clearRepoAfterTests(){
        repository.deleteAll();
    }

    @Test
    void shouldMapMessageToMessageDto() {
        Message message = repository.save(Message.builder()
                .email(TestConstants.EMAIL_1)
                .title(TestConstants.TITLE_1)
                .content(TestConstants.CONTENT_1)
                .magicNumber(TestConstants.MAGIC_NUMBER_1)
                .build());
        MessageDTO messageDTO = MessageMapper.entityToDTO(message);
        assertThat(message.getEmail()).isEqualTo(messageDTO.getEmail());
        assertThat(message.getTitle()).isEqualTo(messageDTO.getTitle());
        assertThat(message.getContent()).isEqualTo(messageDTO.getContent());
        assertThat(message.getMagicNumber()).isEqualTo(messageDTO.getMagicNumber());
        assertThat(message.getMessageId()).isEqualTo(messageDTO.getDto_id());
    }

    @Test
    void shouldMapUiRequestToMessage(){
        UiRequest uiRequest = UiRequest.builder()
                .email(TestConstants.EMAIL_2)
                .title(TestConstants.TITLE_2)
                .content(TestConstants.CONTENT_2)
                .magic_number(TestConstants.MAGIC_NUMBER_2)
                .build();

        Message message = MessageMapper.requestToMessage(uiRequest);

        assertThat(message.getEmail()).isEqualTo(uiRequest.getEmail());
        assertThat(message.getTitle()).isEqualTo(uiRequest.getTitle());
        assertThat(message.getContent()).isEqualTo(uiRequest.getContent());
        assertThat(message.getMagicNumber()).isEqualTo(uiRequest.getMagic_number());
        assertThat(message.getMessageId()).isNull();
    }

    @Test
    void shouldMapMessageWithNullFieldsToMessageDto(){
        Message message = repository.save(Message.builder()
                .email(TestConstants.EMAIL_1)
                .title(null)
                .content(null)
                .magicNumber(TestConstants.MAGIC_NUMBER_1)
                .build());

        MessageDTO messageDTO = MessageMapper.entityToDTO(message);
        assertThat(message.getEmail()).isEqualTo(messageDTO.getEmail()).isNotNull();
        assertThat(message.getTitle()).isNull();
        assertThat(message.getContent()).isNull();
        assertThat(message.getMagicNumber()).isEqualTo(messageDTO.getMagicNumber()).isNotNull();
        assertThat(message.getMessageId()).isEqualTo(messageDTO.getDto_id()).isNotNull();
    }

    @Test
    void shouldMapUiRequestWithNullFieldsToMessage(){
        UiRequest uiRequest = UiRequest.builder()
                .email(null)
                .title(TestConstants.TITLE_2)
                .content(TestConstants.CONTENT_2)
                .magic_number(null)
                .build();
        Message message = MessageMapper.requestToMessage(uiRequest);

        assertThat(message.getEmail()).isNull();
        assertThat(message.getTitle()).isEqualTo(uiRequest.getTitle()).isNotNull();
        assertThat(message.getContent()).isEqualTo(uiRequest.getContent()).isNotNull();
        assertThat(message.getMagicNumber()).isNull();
        assertThat(message.getMessageId()).isNull();
    }


}