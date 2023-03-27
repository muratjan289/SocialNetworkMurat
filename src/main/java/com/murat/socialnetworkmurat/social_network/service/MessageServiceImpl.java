package com.murat.socialnetworkmurat.social_network.service;


import com.murat.socialnetworkmurat.social_network.DAO.MessageRepository;
import com.murat.socialnetworkmurat.social_network.entity.Messages;
import com.murat.socialnetworkmurat.social_network.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    public MessageRepository messageRepository;


    @Override
    public List<Messages> getAllMessages() {
        return messageRepository.findAll();
    }

    public Messages getMessage(int id){
        Messages messages = null;
        Optional<Messages> optional = messageRepository.findById(id);
        if(optional.isPresent()){
            messages = optional.get();
        }
        return messages;
    }

    @Override
    public void saveMessage(Messages messages){
        messageRepository.save(messages);
    }


    @Override
    public void deleteMessage(int id){
        messageRepository.deleteById(id);

    }





}
    


    




