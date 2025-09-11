package br.com.apimessage.controller;

import br.com.apimessage.dto.MessageDTO;
import br.com.apimessage.service.MessageService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<MessageDTO>> getMessages() {
        return ResponseEntity.ok(messageService.getMessage());
    }

    @PostMapping
    public ResponseEntity<MessageDTO> postMessage(@RequestBody @Valid MessageDTO messageDTO) {

        return ResponseEntity.ok(messageService.sendMessage(messageDTO));
    }



}
