package pl.wojcik.taskwithmongo.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UiRequest {
    private String email;
    private String title;
    private String content;
    private Integer magic_number;
}
