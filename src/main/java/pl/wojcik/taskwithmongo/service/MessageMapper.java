package pl.wojcik.taskwithmongo.service;

import pl.wojcik.taskwithmongo.model.dto.MessageDTO;
import pl.wojcik.taskwithmongo.model.message.Message;
import pl.wojcik.taskwithmongo.model.request.UiRequest;

public class MessageMapper {

    public static Message requestToMessage(UiRequest r){
        return Message.builder()
                .email(r.getEmail())
                .content(r.getContent())
                .title(r.getTitle())
                .magicNumber(r.getMagic_number())
                .build();
    }

    public static MessageDTO entityToDTO(Message m) {
        return MessageDTO.builder()
                .dto_id(m.getMessageId())
                .email(m.getEmail())
                .content(m.getContent())
                .title(m.getTitle())
                .magicNumber(m.getMagicNumber())
                .build();
    }

}
