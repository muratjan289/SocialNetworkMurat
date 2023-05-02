package com.murat.socialnetworkmurat.social_network.Controller;

import com.murat.socialnetworkmurat.social_network.DAO.UserRepository;
import com.murat.socialnetworkmurat.social_network.entity.Roles;
import com.murat.socialnetworkmurat.social_network.entity.User;
import com.murat.socialnetworkmurat.social_network.service.UserService;
import com.murat.socialnetworkmurat.social_network.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yaml")
class UserRESTControllerTest {

    Roles roles;

    @Autowired
   private UserRESTController userRESTController;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private UserServiceImpl userService;
    private static final Logger log = Logger.getLogger(UserRESTController.class.getName());

    User user1 = new User("John","nolovicj","Vse ok",1,"No image",new Roles("admin") );
    User user = new User( "DIma","LOcked","RossiyaZZZ",1,"No image",new Roles("user"));

    @Test
    void showAllUsers() {
    }



    @Test
    void testAddNewUser() {
        // create a new user1
        // save the user1
        userRESTController.addNewUser(user1);
        // check that the user1 is saved with the correct properties
        Optional<User> savedUser = usersRepository.findById(user1.getId());
        assertTrue(savedUser.isPresent());
        assertEquals(user1.getName(), savedUser.get().getName());
        assertEquals(user1.getLogin(), savedUser.get().getLogin());
        assertEquals(user1.getStatus(), savedUser.get().getStatus());
        assertEquals(user1.getEnabled(), savedUser.get().getEnabled());
        assertEquals(user1.getImage(), savedUser.get().getImage());
        assertEquals(user1.getRoles().getId(), savedUser.get().getRoles().getId());
    }


    @Test
    public void testUpdateUser() {
        // Создаем пользователя для обновления
       userRESTController.addNewUser(user1);
       Optional <User> savedUser = usersRepository.findById(user1.getId());
       int idUserBeforeSave = user1.getId();
        User user2 = new User( idUserBeforeSave,"DIma","LOcked","RossiyaZZZ",1,"No image",new Roles("admin"));
        savedUser = Optional.ofNullable(userRESTController.updateUser(user2));
        int idUserAfterSafe = savedUser.get().getId();
        assertEquals(idUserBeforeSave,idUserAfterSafe);
        // Проверяем, что возвращенный методом updateUser() объект updatedUser соответствует ожидаемому объекту user1

    }

    @Test
    public void testDeleteUser() {
        userRESTController.addNewUser(user1);
        userRESTController.deleteUser(user1.getId());

    }




    @Test
    void testAddFriend() {
        userRESTController.addNewUser(user1);
        userRESTController.addNewUser(user);
        ResponseEntity<?> result  = userRESTController.addFriend(user1.getId(),user.getId());
        assertEquals(result.getStatusCode(),HttpStatus.OK);


    }


    @Test
    void testDeleteUserFriend() {
        userRESTController.addNewUser(user1);
        userRESTController.addNewUser(user);
        userRESTController.addFriend(user1.getId(),user.getId());
        userRESTController.deleteUserFriend(user1.getId(),user.getId());

    }


}