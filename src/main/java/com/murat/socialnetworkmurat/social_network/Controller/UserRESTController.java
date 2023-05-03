package com.murat.socialnetworkmurat.social_network.Controller;

import com.murat.socialnetworkmurat.social_network.entity.User;
import com.murat.socialnetworkmurat.social_network.exceptionHandling.NoSuchUserException;
import com.murat.socialnetworkmurat.social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api")
public class UserRESTController {

    private static final Logger log = Logger.getLogger(UserRESTController.class.getName());

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<User> showAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        log.info("show ALl Users  and amount = " + allUsers.size());
        return allUsers;
    }


    @GetMapping("/users/{userId}/friends")
    public Optional<Object> showFriends(@PathVariable int userId) {
        log.info("Show friends user who ID = " + userId);
        return userService.showFriends(userId);
    }

    @GetMapping("/users/{userId}/onlyfriends")
    public ResponseEntity<List<Integer>> showJustFriends(@PathVariable int userId) {
        List<Integer> friendIds = userService.showJustFriends(userId);
        if (friendIds == null) {
            return ResponseEntity.notFound().build();
        }

        log.info("Show friends table users_has_users and number of friends  = " + friendIds.size() + " friendships");
        return ResponseEntity.ok(friendIds);
    }


    /**
     * This method provides a REST API endpoint to retrieve a user from the database by their ID.
     * It takes in the user ID as a path variable and uses the userService.getUser() method to fetch the user from the database.
     * If the user is not found, it throws a NoSuchUserException with an appropriate error message.
     * It logs the user ID of the retrieved user using log.info().
     *
     * @param id - an integer representing the ID of the user to retrieve
     * @return an Optional<User> object representing the retrieved user, or an empty Optional if the user is not found
     * @throws NoSuchUserException if no user is found with the given ID
     */
    @GetMapping("/users/{id}")
    public Optional<User> getUser(@PathVariable int id) {
        Optional<User> userOptional = userService.getUser(id);
        if (userOptional.isEmpty()){
            throw new NoSuchUserException("There is no employee with ID = " + id + "int Database");
        }
        log.info("Show user there ID = " + id);
        return userOptional;
    }


    /**
     * This method provides a REST API endpoint to add a new user to the database.
     * It takes in a User object in the request body and saves it to the database using the userService.saveUser() method.
     * It logs the user ID of the newly added user using log.info().
     *
     * @param user - a User object representing the new user information
     * @return the newly added User object
     */
    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        log.info("New User  with id= " + getUser(user.getId()));
        return user;
    }

    /**
     * This method provides a REST API endpoint to update a user in the database based on their ID.
     * It takes in a User object in the request body and saves it to the database using the userService.saveUser() method.
     * It logs the user ID of the updated user using log.info().
     *
     * @param user - a User object representing the updated user information
     * @return the updated User object
     */

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user) {
        userService.saveUser(user);
        log.info("User with ID = " + (user.getId()) + " was changed ");
        return user;
    }

    /**
     * This method provides a REST API endpoint to delete a user from the database based on their ID.
     * It takes in the user ID as a path variable and returns a string indicating that the user was successfully deleted.
     * If the user with the specified ID does not exist in the database, it throws a custom exception called NoSuchUserException.
     * It logs the user ID of the deleted user using log.info().
     *
     * @param id - an integer representing the ID of the user to be deleted
     * @return a string indicating that the user was successfully deleted
     * @throws NoSuchUserException if the user with the specified ID does not exist in the database
     */
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        Optional<User> user = userService.getUser(id);
        if (user != null) {

            throw new NoSuchUserException("There is no employee with ID = " +
                    id + "in Database");
        }
        userService.deleteUser(id);
        log.info("User with ID = " + id + " was deleted");
        return "User with ID = " + id + " was deleted";

    }


    /**
     * This method provides a REST API endpoint to add a new friendship between two users with the given IDs.
     * It takes in the user ID as a path variable and the friend ID as a request parameter and returns a ResponseEntity
     * indicating whether the addition was successful or not. If either user or friend does not exist, it throws a custom
     * exception called NoSuchUserException. It logs the user and friend IDs whose friendship was added using log.info().
     *
     * @param userId - an integer representing the ID of the user who wants to add a new friend
     * @param friendId - an integer representing the ID of the friend being added
     * @return a ResponseEntity indicating whether the addition was successful or not
     * @throws NoSuchUserException if either user or friend does not exist
     */
// If you want to add new friendship you need added for this API "?friendId="
    @PostMapping("/users/{userId}/friends")
    public ResponseEntity<?> addFriend(@PathVariable int userId, @RequestParam int friendId) {
        boolean result = userService.addFriend(userId, friendId);
        if (result) {
            log.info("New friendship between who ID = " + userId + " and " + friendId);
            return ResponseEntity.ok().build();
        } else {
            throw new NoSuchUserException("There is  user with ID = " + userId + "or " + friendId + "no such");
        }
    }

    /**
     * This method provides a REST API endpoint to delete a friendship between two users with the given IDs.
     * It takes in the user ID and friend ID as path variables and returns a ResponseEntity indicating whether
     * the deletion was successful or not. If the friendship does not exist, it throws a custom exception called NoSuchUserException.
     * It logs the user and friend IDs whose friendship was deleted using log.info().
     *
     * @param userId - an integer representing the ID of the user whose friendship is being deleted
     * @param friendId - an integer representing the ID of the friend whose friendship is being deleted
     * @return a ResponseEntity indicating whether the deletion was successful or not
     * @throws NoSuchUserException if either user or friend does not exist
     */
    @DeleteMapping("/users/{userId1}/friends/{friendId2}")
    public ResponseEntity<?> deleteUserFriend(@PathVariable int userId, @PathVariable int friendId) {
        boolean success = userService.deleteUserFriend(userId, friendId);
        if (success) {
            log.info("Friendship between who ID = " + userId + " and " + friendId + "was deleted");
            return ResponseEntity.ok().build();
        }
        {
            throw new NoSuchUserException("The friendship between who ID = " + userId + " and "+ friendId + "don't deleted");
        }
    }


}


//        @PostMapping("/{userId}/friends/{friendId}")
//    public ResponseEntity<?> addFriend(@PathVariable("userId") int userId, @PathVariable("friendId") int friendId) {
//        User user = us.findById(userId);
//        User friend = .findById(friendId);
//        userService.addFriend(user, friend);
//        return ResponseEntity.ok().build();
//    }    }
