package com.murat.socialnetworkmurat.social_network.service;


import com.murat.socialnetworkmurat.social_network.DAO.MessageRepository;
import com.murat.socialnetworkmurat.social_network.DAO.UserRepository;
import com.murat.socialnetworkmurat.social_network.entity.Messages;
import com.murat.socialnetworkmurat.social_network.entity.User;
import com.murat.socialnetworkmurat.social_network.exceptionHandling.NoSuchMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    public MessageRepository messageRepository;

    @Autowired UserRepository userRepository;
    private static final Logger log = Logger.getLogger(MessageServiceImpl.class.getName());



    /**
     * Returns a list of all messages, up to 10 per page.
     * @param page Page number
     * @return List of up to 10 Messages
     */
    @Override
    public List<Messages> getAllMessages(int page) {
        int pageSize = 10;
        PageRequest pageable = PageRequest.of(page -1, pageSize);
        log.info("Retrieved messages from page " +  page);
        return messageRepository.findAll(pageable).getContent();
    }

    /**
     * Returns a message by ID.
     * @param id ID of the message
     * @return Message object
     */
    public Messages getMessage(int id){
        Messages messages = null;
        Optional<Messages> optional = messageRepository.findById(id);
        if(optional.isPresent()){
            messages = optional.get();
        }
        log.info("Retrieved messages from page " +  id);
        return messages;
    }




    /**
     * Saves a new message to the database.
     * @param messages Message object to be saved
     * @throws NoSuchMessageException if the sender or receiver user does not exist or is not a friend of the sender
     */
    @Override
    public void saveMessage(Messages messages){
        User sender = userRepository.findById(messages.getFrom()).orElse(null);
        User receiver = userRepository.findById(messages.getTo()).orElse(null);
        if (sender == null || receiver == null) {
            throw new NoSuchMessageException("You can't send message if user doesnt exist check your details" );
        }

        if (!sender.getFriends().contains(receiver) || !receiver.getFriends().contains(sender)) {
            throw new NoSuchMessageException("You can't send message if user " + messages.getTo()  + "is not  as a friend ");

        }
        if (sender.getFriends().contains(receiver) && receiver.getFriends().contains(sender)) {
            // проверяем, что пользователь является другом, чтобы можно было отправлять сообщения
            messageRepository.save(messages);
            log.info("Retrieved new  messages from page " +  messages);
        }
    }


    /**
     * Deletes a message from the database by ID.
     * @param id ID of the message to be deleted
     */
    @Override
    public void deleteMessage(int id)  {
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
        } else {
            throw new NoSuchMessageException("This message does not exist ");
        }
    }




}
    


    




