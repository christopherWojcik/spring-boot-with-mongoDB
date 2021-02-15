package pl.wojcik.taskwithmongo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.wojcik.taskwithmongo.aop.AdditionalAuthentication;
import pl.wojcik.taskwithmongo.aop.AdditionalAuthenticationDto;
import pl.wojcik.taskwithmongo.model.dto.MessageDTO;
import pl.wojcik.taskwithmongo.model.request.SendRequest;
import pl.wojcik.taskwithmongo.model.request.UiRequest;
import pl.wojcik.taskwithmongo.repository.MessageRepository;
import pl.wojcik.taskwithmongo.service.MessageService;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
@EnableMongoRepositories(basePackageClasses = MessageRepository.class)
public class ApiController {

    private final MessageService service;
    private final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @ApiOperation(value = "Find all messages by email.",
            response = MessageDTO.class,
            httpMethod = "GET",
            notes = "Email value should not be null. It is also validated for correctness.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "NO CONTENT !"),
            @ApiResponse(code = 400, message = "BAD REQUEST !")
    })
    @GetMapping("/messages/{emailValue}")
    public ResponseEntity<List<MessageDTO>> getAllMessagesByEmail(@PathVariable String emailValue) {
        LOGGER.info("Get all messages by email: {} -> service layer", emailValue);
        return service.getAllMessagesByEmailValue(emailValue);
    }

    @PostMapping("/send")
    public ResponseEntity<List<MessageDTO>> getMessagesContentWithMagicNumber(@RequestBody SendRequest request) {
        LOGGER.info("All messages by magic_number: {} -> service layer", request.getMagic_number());
        return service.printMessagesContentWithMagicNumber(request.getMagic_number());
    }

    @PostMapping("/message")
    public ResponseEntity<MessageDTO> createMessage(@RequestBody UiRequest request) {
        LOGGER.info("Saving to DB message: {} -> service layer", request.getEmail());
        return service.saveToDB(request);
    }

    @PostMapping("/aop/")
    @AdditionalAuthentication
    public ResponseEntity<List<MessageDTO>> getAllMessages(
            @RequestBody AdditionalAuthenticationDto credentials,
            @AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {

        LOGGER.info("We can do something with user: " + user.getPrincipal().toString());
        return service.getAll();
    }

}
