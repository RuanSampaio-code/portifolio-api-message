package br.com.apimessage.controller;

import br.com.apimessage.dto.MessageDTO;
import br.com.apimessage.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

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

        MessageDTO msg1 = new MessageDTO("John Doe", "a", "Hello", null);
        MessageDTO msg2 = new MessageDTO("Jane Doe", "b", "Hi", null);
        List<MessageDTO> messages = Arrays.asList(msg1, msg2);

        when(messageService.getMessage()).thenReturn(messages);

        ResponseEntity<List<MessageDTO>> response = messageController.getMessages();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(messages, response.getBody());
        verify(messageService, times(1)).getMessage();


    }

    @Test
    void postMessage() {

        MessageDTO msg1 = new MessageDTO("John Doe", "a", "Hello", null);
        MessageDTO msg2 = new MessageDTO("Jane Doe", "b", "Hi", null);
        List<MessageDTO> messages = Arrays.asList(msg1, msg2);

        when(messageService.getMessage()).thenReturn(messages);

        ResponseEntity<List<MessageDTO>> response = messageController.getMessages();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(messages, response.getBody());
        verify(messageService, times(1)).getMessage();
    }
}