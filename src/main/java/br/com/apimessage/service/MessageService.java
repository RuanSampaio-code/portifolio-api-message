package br.com.apimessage.service;

import br.com.apimessage.dto.MessageDTO;
import br.com.apimessage.model.Message;
import br.com.apimessage.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<MessageDTO> getMessage() {
        List<Message> messages = messageRepository.findAll();

        List<MessageDTO> messageDTOs = messages.stream()
                .map(message -> new MessageDTO(
                        message.getName(),
                        message.getEmail(),
                        message.getMessage()
                ))
                .toList();

        return messageDTOs;



    }

    public MessageDTO sendMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setName(messageDTO.name());
        message.setEmail(messageDTO.email());
        message.setMessage(messageDTO.message());

        messageRepository.save(message);

        return messageDTO;
    }
}
