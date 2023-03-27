package com.murat.socialnetworkmurat.social_network.Controller;


import com.murat.socialnetworkmurat.social_network.entity.Messages;
import com.murat.socialnetworkmurat.social_network.entity.User;
import com.murat.socialnetworkmurat.social_network.service.MessageService;
import com.murat.socialnetworkmurat.social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
@RequestMapping("/api")
public class MessageRestController {



    @Autowired
   private MessageService messageService;

    @GetMapping("/messages")
    public List<Messages> showAllMessages() {
        List<Messages> allMessages =messageService.getAllMessages();
        return allMessages;
    }


    @GetMapping("/messages/{id}")
    public Messages getEmployee(@PathVariable int id){
        Messages message = messageService.getMessage(id);
        return message;
    }


    @PostMapping("/messages")
    public Messages addNewMessages(@RequestBody Messages messages) {
        messageService.saveMessage(messages);
        return messages;
    }

    @PutMapping("/messages")
    public Messages updateMessages(@RequestBody Messages messages) {
        messageService.saveMessage(messages);
        return messages;
    }

    @RequestMapping(value ="/messages/{id}", method = DELETE)
    public String deleteMessage(@PathVariable int id) {
        Messages messages = messageService.getMessage(id);

//        if(user != null){
//
//            throw new NoSuchEmployeeException("There is no employee with ID = " +
//                    id + "in Database");
//
//
        messageService.deleteMessage(id);
        return "Message with ID = " + id + " was deleted";
}
}
