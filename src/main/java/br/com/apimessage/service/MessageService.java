package br.com.apimessage.service;

import br.com.apimessage.dto.MessageDTO;
import br.com.apimessage.dto.MessageResponseDTO;
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

    // Este método agora retorna uma lista do DTO de resposta
    public List<MessageResponseDTO> getMessage() {
        List<Message> messages = messageRepository.findAll();

        return messages.stream()
                .map(message -> new MessageResponseDTO(
                        message.getId(),
                        message.getName(),
                        message.getEmail(),
                        message.getMessage(),
                        message.getCreatedAt() // Agora incluindo a data
                ))
                .toList();
    }

    // Este método agora retorna o DTO de resposta
    public MessageResponseDTO sendMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setName(messageDTO.name());
        message.setEmail(messageDTO.email());
        message.setMessage(messageDTO.message());

        // Salve a entidade no banco. A variável 'messageSalva'
        // conterá o objeto completo, com ID e createdAt.
        Message messageSalva = messageRepository.save(message);

        // Crie e retorne um NOVO DTO com os dados que foram salvos
        return new MessageResponseDTO(
                messageSalva.getId(),
                messageSalva.getName(),
                messageSalva.getEmail(),
                messageSalva.getMessage(),
                messageSalva.getCreatedAt()
        );
    }
}
