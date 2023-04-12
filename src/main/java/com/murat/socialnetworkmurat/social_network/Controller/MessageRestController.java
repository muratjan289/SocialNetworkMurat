package com.murat.socialnetworkmurat.social_network.Controller;


import com.murat.socialnetworkmurat.social_network.entity.Messages;
import com.murat.socialnetworkmurat.social_network.entity.User;
import com.murat.socialnetworkmurat.social_network.exceptionHandling.NoSuchMessageException;
import com.murat.socialnetworkmurat.social_network.service.MessageService;
import com.murat.socialnetworkmurat.social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
@RequestMapping("/api")
public class MessageRestController {

    /**
     * This class contains methods for managing messages.
     */
    private static final Logger log = Logger.getLogger(UserRESTController.class.getName());



    @Autowired
   private MessageService messageService;


    /**
     * Retrieves a list of messages for a specific page number.
     *
     * @param page the page number to retrieve messages for
     * @return a list of messages for the specified page
     * example /messages?page=1
     */
    @GetMapping("/messages")
    public List<Messages> showAllMessages(@RequestParam int page) {
        log.info("Show 10 messages on the page number" + page );
        return messageService.getAllMessages(page);
    }

    /**
     * Retrieves a message with the specified ID.
     *
     * @param id the ID of the message to retrieve
     * @return the message with the specified ID
     */
    @GetMapping("/messages/{id}")
    public Messages getEmployee(@PathVariable int id){
        Messages message = messageService.getMessage(id);
        return message;
    }


    /**
     * Saves a new message to the database.
     *
     * @param messages the message to save
     * @return the saved message
     */
    @PostMapping("/messages")
    public Messages sendNewMessages(@RequestBody Messages messages) {
        messageService.saveMessage(messages);
        return messages;
    }

    /**
     * Updates an existing message in the database.
     *
     * @param messages the message to update
     * @return the updated message
     */
    @PutMapping("/messages")
    public Messages updateMessages(@RequestBody Messages messages) {
        if(messages == null) {
            throw new NoSuchMessageException("There is no employee with ID = " +
                    messages.getId() + "in Database");

        }
        else {
        messageService.saveMessage(messages);
        log.info("The messages who ID = " + messages.getId() + " wa" );
        }
        return messages;

    }



    /**
     * Deletes a message with the specified ID from the database.
     *
     * @param id the ID of the message to delete
     * @return a string indicating that the message was deleted
     * @throws NoSuchMessageException if no message with the specified ID is found
     */
    @RequestMapping(value ="/messages/{id}", method = DELETE)
    public String deleteMessage(@PathVariable int id) {
        Messages messages = messageService.getMessage(id);

        if(messages == null) {

            throw new NoSuchMessageException("There is no employee with ID = " +
                    id + "in Database");

        }
        else {
            messageService.deleteMessage(id);
        }
        return "Message with ID = " + id + " was deleted";
}
}
