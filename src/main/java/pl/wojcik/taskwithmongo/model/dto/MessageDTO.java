package pl.wojcik.taskwithmongo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private String dto_id;
    private String email;
    private String title;
    private String content;
    private Integer magicNumber;
}
