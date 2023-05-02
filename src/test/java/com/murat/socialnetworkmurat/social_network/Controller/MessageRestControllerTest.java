package com.murat.socialnetworkmurat.social_network.Controller;

import com.murat.socialnetworkmurat.social_network.DAO.MessageRepository;
import com.murat.socialnetworkmurat.social_network.entity.Messages;
import com.murat.socialnetworkmurat.social_network.entity.Roles;
import com.murat.socialnetworkmurat.social_network.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yaml")
class MessageRestControllerTest {


    @Autowired
    private UserRESTController userRESTController;

    @Autowired
    private MessageRestController messageRestController;

    private MessageRepository messageRepository;
    User user1 = new User("John","Deer","Vse ok",1,"No image",new Roles("admin") );
    User user = new User( "Locked","Martin","RossiyaZZZ",1,"No image",new Roles("user"));
    Messages  messages = new Messages("urak misha",15,16);

    @Test
    void showAllMessages() {


    }

    @Test
    void getMessage() {
        messageRestController.sendNewMessages(messages);
        int idMessage =  messages.getId();
        messageRestController.getMessage(idMessage);

    }

    @Test
    void sendNewMessages() {
        User user2 = new User("Stas","Deer","Vse ok",1,"No image",new Roles("admin") );
        User user3 = new User( "Locked","Martin","RossiyaZZZ",1,"No image",new Roles("user"));
        userRESTController.addNewUser(user2);
        userRESTController.addNewUser(user3);
        userRESTController.addFriend(user2.getId(),user3.getId());
        Messages  message1 = new Messages("Durak misha", user2.getId(), user3.getId());
        messageRestController.sendNewMessages(message1);




    }



    @Test
    void testDeleteMessage() {
        messageRestController.sendNewMessages(messages);
        int idMessage =  messages.getId();
        messageRestController.deleteMessage(idMessage);
    }
}