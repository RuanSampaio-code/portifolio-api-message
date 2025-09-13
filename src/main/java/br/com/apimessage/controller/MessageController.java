package br.com.apimessage.controller;

import br.com.apimessage.dto.MessageDTO;
import br.com.apimessage.dto.MessageResponseDTO;
import br.com.apimessage.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<MessageResponseDTO>> getMessages() {
        return ResponseEntity.ok(messageService.getMessage());
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> postMessage(@RequestBody @Valid MessageDTO messageDTO) {
        MessageResponseDTO responseDTO = messageService.sendMessage(messageDTO);
        // Use o status 201 Created para novas criações, é uma melhor prática
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }


}
