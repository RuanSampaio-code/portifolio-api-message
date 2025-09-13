package br.com.apimessage.controller;

import br.com.apimessage.dto.MessageDTO;
import br.com.apimessage.dto.MessageResponseDTO;
import br.com.apimessage.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class MessageControllerTest {

    private MessageService messageService;
    private MessageController messageController;

    @BeforeEach
    void setUp() {
        messageService = mock(MessageService.class);
        messageController = new MessageController(messageService);
    }

    @Test
    void getMessages() {
        MessageResponseDTO msg1 = new MessageResponseDTO("1", "John Doe", "a", "Hello", LocalDateTime.now());
        MessageResponseDTO msg2 = new MessageResponseDTO("2", "Jane Doe", "b", "Hi", LocalDateTime.now());
        List<MessageResponseDTO> messages = Arrays.asList(msg1, msg2);

        when(messageService.getMessage()).thenReturn(messages);

        ResponseEntity<List<MessageResponseDTO>> response = messageController.getMessages();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(messages, response.getBody());
        verify(messageService, times(1)).getMessage();
    }

    @Test
    void postMessage() {
        MessageDTO request = new MessageDTO("John Doe", "a", "Hello");
        MessageResponseDTO responseDTO = new MessageResponseDTO("1", "John Doe", "a", "Hello", LocalDateTime.now());

        when(messageService.sendMessage(request)).thenReturn(responseDTO);

        ResponseEntity<MessageResponseDTO> response = messageController.postMessage(request);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(responseDTO, response.getBody());
        verify(messageService, times(1)).sendMessage(request);
    }
}