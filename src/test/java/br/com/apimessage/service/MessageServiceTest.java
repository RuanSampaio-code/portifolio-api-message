package br.com.apimessage.service;

import br.com.apimessage.dto.MessageDTO;
import br.com.apimessage.model.Message;
import br.com.apimessage.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MessageServiceTest {

    private MessageRepository messageRepository;
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        messageRepository = mock(MessageRepository.class);
        messageService = new MessageService(messageRepository);
    }

    @Test
    void getMessage() {
        Message msg1 = new Message();
        msg1.setName("John Doe");
        msg1.setEmail("a");
        msg1.setMessage("Hello");
        msg1.setCreatedAt(null);

        Message msg2 = new Message();
        msg2.setName("Jane Doe");
        msg2.setEmail("b");
        msg2.setMessage("Hi");
        msg2.setCreatedAt(null);

        List<Message> messages = Arrays.asList(msg1, msg2);

        when(messageRepository.findAll()).thenReturn(messages);

        List<MessageDTO> result = messageService.getMessage();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).name());
        assertEquals("Jane Doe", result.get(1).name());
        verify(messageRepository, times(1)).findAll();
    }

    @Test
    void sendMessage() {
        MessageDTO dto = new MessageDTO("John Doe", "a", "Hello", null);

        Message savedMessage = new Message();
        savedMessage.setName(dto.name());
        savedMessage.setEmail(dto.email());
        savedMessage.setMessage(dto.message());
        savedMessage.setCreatedAt(dto.createdAt());

        when(messageRepository.save(any(Message.class))).thenReturn(savedMessage);

        MessageDTO result = messageService.sendMessage(dto);

        assertEquals(dto, result);
        verify(messageRepository, times(1)).save(any(Message.class));
    }
}