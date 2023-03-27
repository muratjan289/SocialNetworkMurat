package com.murat.socialnetworkmurat;

import com.murat.socialnetworkmurat.social_network.DAO.MessageRepository;
import com.murat.socialnetworkmurat.social_network.DAO.UserRepository;
import com.murat.socialnetworkmurat.social_network.entity.Messages;
import com.murat.socialnetworkmurat.social_network.entity.Roles;
import com.murat.socialnetworkmurat.social_network.entity.User;
import com.murat.socialnetworkmurat.social_network.service.MessageServiceImpl;
import com.murat.socialnetworkmurat.social_network.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SocialNetworkMuratApplicationTests {


    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private UserServiceImpl userService;

    @Test
    void contextLoads() {
    }



    @Test
    public void testSaveMessageUsers() {
        User user1 = new User(18,"John","nolovicj","Vse ok",1,"No image");
        User user2 = new User(20, "DIma","LOcked","RossiyaZZZ",1,"No image");
        Messages message = new Messages("Hello, world!", user1.getId(), user2.getId());

        message.setUsers(Arrays.asList(user1, user2));
        Assertions.assertEquals(2, message.getUsers().size());
    }


    @Test
    public void testSaveUser() {
        // create a new user
        User user = new User(1,"John","nolovicj","Vse ok",1,"No image","admin" );

        // save the user
        userService.saveUser(user);

        // check that the user is saved with the correct properties
        User savedUser = usersRepository.findById(user.getId()).orElse(null);
        assertEquals(user.getName(), savedUser.getName());
        assertEquals(user.getLogin(), savedUser.getLogin());
        assertEquals(user.getStatus(), savedUser.getStatus());
        assertEquals(user.getEnabled(), savedUser.getEnabled());
        assertEquals(user.getImage(), savedUser.getImage());




    }



}
