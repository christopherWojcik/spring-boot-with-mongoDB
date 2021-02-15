package pl.wojcik.taskwithmongo.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wojcik.taskwithmongo.bootstrap.DBInitializerData;
import pl.wojcik.taskwithmongo.controller.AbstractSpringTest;
import pl.wojcik.taskwithmongo.model.message.Message;
import pl.wojcik.taskwithmongo.service.TestConstants;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessageRepositoryTest extends AbstractSpringTest {

    @Autowired
    private MessageRepository repository;

    @BeforeEach
    void clearRepoBeforeTest(){
        repository.deleteAll();
    }

    @AfterEach
    void clearRepoAfterTest(){
        repository.deleteAll();
    }

    @Test
    void shouldFinaAllMessagesFormDB() {
        Message m1 = Message.builder()
                .email(DBInitializerData.EMAIL_1)
                .title(DBInitializerData.TITLE_1)
                .content(DBInitializerData.CONTENT_1)
                .magicNumber(DBInitializerData.MAGIC_NUMBER_1)
                .build();
        Message m2 = Message.builder()
                .email(DBInitializerData.EMAIL_2)
                .title(DBInitializerData.TITLE_2)
                .content(DBInitializerData.CONTENT_2)
                .magicNumber(DBInitializerData.MAGIC_NUMBER_1)
                .build();
        Message m3 = Message.builder()
                .email(DBInitializerData.EMAIL_1)
                .title(DBInitializerData.TITLE_3)
                .content(DBInitializerData.CONTENT_3)
                .magicNumber(DBInitializerData.MAGIC_NUMBER_3)
                .build();
        Message m4 = Message.builder()
                .email(DBInitializerData.EMAIL_3)
                .title(DBInitializerData.TITLE_2)
                .content(DBInitializerData.CONTENT_2)
                .magicNumber(DBInitializerData.MAGIC_NUMBER_4)
                .build();
        Message m5 = Message.builder()
                .email(DBInitializerData.EMAIL_4)
                .title(DBInitializerData.TITLE_5)
                .content(DBInitializerData.CONTENT_4)
                .magicNumber(DBInitializerData.MAGIC_NUMBER_4)
                .build();
        Message m6 = Message.builder()
                .email(DBInitializerData.EMAIL_5)
                .title(DBInitializerData.TITLE_5)
                .content(DBInitializerData.CONTENT_4)
                .magicNumber(DBInitializerData.MAGIC_NUMBER_4)
                .build();
        repository.save(m1);
        repository.save(m2);
        repository.save(m3);
        repository.save(m4);
        repository.save(m5);
        repository.save(m6);

        List<Message> all = repository.findAll();
        assertThat(all.size()).isEqualTo(6);
    }

    @Test
    void shouldFindMessagesByEmail(){
        // example from tak description
        Message m1 = Message.builder()
                .email(TestConstants.EMAIL_1)
                .title(TestConstants.TITLE_1)
                .content(TestConstants.CONTENT_1)
                .magicNumber(TestConstants.MAGIC_NUMBER_1)
                .build();
        Message m2 = Message.builder()
                .email(TestConstants.EMAIL_1)
                .title(TestConstants.TITLE_2)
                .content(TestConstants.CONTENT_2)
                .magicNumber(TestConstants.MAGIC_NUMBER_2)
                .build();
        Message m3 = Message.builder()
                .email(TestConstants.EMAIL_2)
                .title(TestConstants.TITLE_2)
                .content(TestConstants.CONTENT_2)
                .magicNumber(TestConstants.MAGIC_NUMBER_1)
                .build();
        repository.save(m1);
        repository.save(m2);
        repository.save(m3);

        List<Message> messagesByEmail = repository.findMessagesByEmail(TestConstants.EMAIL_1);
        assertThat(messagesByEmail.size()).isEqualTo(2);
    }

}