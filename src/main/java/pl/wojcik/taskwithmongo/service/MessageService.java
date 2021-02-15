package pl.wojcik.taskwithmongo.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wojcik.taskwithmongo.exceptions.ApiRequestException;
import pl.wojcik.taskwithmongo.exceptions.ExceptionConstants;
import pl.wojcik.taskwithmongo.model.dto.MessageDTO;
import pl.wojcik.taskwithmongo.model.message.Message;
import pl.wojcik.taskwithmongo.model.request.UiRequest;
import pl.wojcik.taskwithmongo.repository.MessageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final Logger LOGGER = LogManager.getLogger(MessageService.class);
    private final MessageRepository repository;

    public ResponseEntity<List<MessageDTO>> getAllMessagesByEmailValue(String email) {
        try {
            if (email == null || EmailValidator.validate(email)) {
                LOGGER.info("Bad request");
                throw new ApiRequestException(ExceptionConstants.BAD_REQUEST_WRONG_EMAIL);
            }
            List<MessageDTO> dtoList = repository.findMessagesByEmail(email).stream()
                    .map(MessageMapper::entityToDTO)
                    .collect(Collectors.toList());

            if (dtoList.isEmpty()) {
                LOGGER.warn("MessageDTO list is empty.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            LOGGER.info("MessageDTO list successfully returned!");
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        } catch (Exception e) {
            throw new ApiRequestException(ExceptionConstants.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<MessageDTO>> printMessagesContentWithMagicNumber(Integer number) {
        try {
            if (number == null || number < 0) {
                LOGGER.error("Exception will be thrown. Wrong number: {}", number);
                throw new ApiRequestException(ExceptionConstants.BAD_REQUEST_WRONG_NUMBER);
            }
            List<MessageDTO> dtoList = repository.findAllByMagicNumber(number)
                    .stream()
                    .map(MessageMapper::entityToDTO)
                    .collect(Collectors.toList());

            if (dtoList.isEmpty()) {
                LOGGER.warn("MessageDTO list is empty.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // logging messages with given magic_number
            dtoList.forEach(LOGGER::info);
            // delete printed message from DB
            dtoList.forEach(m -> deleteFromDB(m.getDto_id()));

            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<MessageDTO> saveToDB(UiRequest request) {
        try {
            Message message = repository.save(MessageMapper.requestToMessage(request));
            MessageDTO messageDTO = MessageMapper.entityToDTO(message);
            LOGGER.info("Message successfully saved!");
            return new ResponseEntity<>(messageDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ApiRequestException(ExceptionConstants.INTERNAL_SERVER_ERROR);
        }
    }

    private void deleteFromDB(String id) {
        LOGGER.info("Message of id: {}, has been deleted", id);
        repository.deleteById(id);
    }

    public ResponseEntity<List<MessageDTO>> getAll() {
        List<MessageDTO> messageDTOS = repository.findAll().stream()
                .map(MessageMapper::entityToDTO).collect(Collectors.toList());
        return new ResponseEntity<>(messageDTOS, HttpStatus.OK);
    }
}
