package com.murat.socialnetworkmurat;

import com.murat.socialnetworkmurat.social_network.DAO.UserRepository;
import com.murat.socialnetworkmurat.social_network.entity.Messages;
import com.murat.socialnetworkmurat.social_network.entity.Roles;
import com.murat.socialnetworkmurat.social_network.entity.User;
import com.murat.socialnetworkmurat.social_network.service.UserServiceImpl;
import jakarta.transaction.Transactional;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yaml")
public  class SocialNetworkMuratApplicationTests {



    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private UserServiceImpl userService;

    @Test
    void contextLoads() {
    }




        User user1 = new User("John","nolovicj","Vse ok",1,"No image",new Roles("user"));
        User user2 = new User(1, "DIma","LOcked","RossiyaZZZ",1,"No image",new Roles(2));






    @Test
    public void testSaveUser() {
        // create a new user
        User user = new User("John","nolovicj","Vse ok",1,"No image",new Roles("admin") );
        // save the user
        userService.saveUser(user);
        // check that the user is saved with the correct properties
        Optional<User> savedUser = usersRepository.findById(user.getId());
        assertTrue(savedUser.isPresent());
        assertEquals(user.getName(), savedUser.get().getName());
        assertEquals(user.getLogin(), savedUser.get().getLogin());
        assertEquals(user.getStatus(), savedUser.get().getStatus());
        assertEquals(user.getEnabled(), savedUser.get().getEnabled());
        assertEquals(user.getImage(), savedUser.get().getImage());
        assertEquals(user.getRoles().getId(), savedUser.get().getRoles().getId());
    }

    @Test
    @Transactional
    public void testDeleteUserFriend() {
        // create users and add friendship
        User user1 = new User( "John", "nolovicj", "Vse ok", 1, "No image", new Roles( "admin"));
        User user4 = new User( "DIma", "LOcked", "RossiyaZZZ", 1, "No image", new Roles("user"));
        user1.getFriends().add(user4);
        user4.getFriends().add(user1);
        usersRepository.save(user1);
        usersRepository.save(user4);

        // delete friend
        boolean result = userService.deleteUserFriend(user1.getId(), user4.getId());

        // check if friend was removed successfully
        assertTrue(result);
        Optional<User> updatedUser1 = usersRepository.findById(user1.getId());
        Optional<User> updatedUser2 = usersRepository.findById(user4.getId());
        if (updatedUser1.isPresent()) {
            assertFalse(updatedUser1.get().getFriends().contains(updatedUser2.get()));
            assertFalse(updatedUser2.get().getFriends().contains(updatedUser1.get()));
        }

    }
    @Test
    public void testDeleteUser(){
        userService.saveUser(user1);
        user1.getId();
        userService.deleteUser(user1.getId());
    }



    }
    



