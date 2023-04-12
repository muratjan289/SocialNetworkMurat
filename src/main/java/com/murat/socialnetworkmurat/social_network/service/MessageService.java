package com.murat.socialnetworkmurat.social_network.service;

import com.murat.socialnetworkmurat.social_network.entity.Messages;

import java.util.List;

public interface MessageService {


//    public List<Messages> getAllMessages();

    List<Messages> getAllMessages(int page);

    public Messages getMessage(int id);

    void saveMessage(Messages messages);

    public void deleteMessage(int id);
}
