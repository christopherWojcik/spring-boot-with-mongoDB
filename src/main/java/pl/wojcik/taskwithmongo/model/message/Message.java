package pl.wojcik.taskwithmongo.model.message;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "message")
public class Message {

    @Id
    private String messageId;
    private String email;
    private String title;
    private String content;
    private Integer magicNumber;


}
