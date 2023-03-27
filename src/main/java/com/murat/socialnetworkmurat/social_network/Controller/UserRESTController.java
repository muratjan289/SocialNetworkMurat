package com.murat.socialnetworkmurat.social_network.Controller;

import com.murat.socialnetworkmurat.social_network.entity.User;
import com.murat.socialnetworkmurat.social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
    @RequestMapping("/api")
    public class UserRESTController {


        @Autowired
        private UserService userService;

        @GetMapping("/users")
        public List<User> showAllUsers() {
            List<User> allUsers = userService.getAllUsers();
            return allUsers;
        }

        @GetMapping("/friends")
        public List<User> showAllFriends(){
            List<User> allFriends = userService.getAllFriend();
            return allFriends;
        }


    @GetMapping("/users/{userId}/friendsPlus")
    public List<User> showFriendsAndFriendsFriends(@PathVariable int userId) {
        return userService.showFriendsPlus(userId);
    }

    @GetMapping("/users/{userId}/friends")
    public List<User> showFriends(@PathVariable int userId) {
        return userService.showFriends(userId);
    }

    @GetMapping("/users/{userId}/onlyfriends")
    public ResponseEntity<List<Integer>> showJustFriends(@PathVariable int userId) {
        List<Integer> friendIds = userService.showJustFriends(userId);
        if (friendIds == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(friendIds);
    }



        @GetMapping("/users/{id}")
        public User getUser(@PathVariable int id) {

            User user = userService.getUser(id);

//            if (user == null) {
//
//                throw new NoSuchEmployeeException("There is no employee with ID = " + id + "int Database");
//            }
            return user;
        }


        @PostMapping("/users")
        public User addNewUser(@RequestBody User user) {

            userService.saveUser(user);
            return user;
        }

        @PutMapping("/users")
        public User updateUser(@RequestBody User user) {
            userService.saveUser(user);
            return user;
        }

        @DeleteMapping("/users/{id}")
        public String deleteUser(@PathVariable int id) {
            User user = userService.getUser(id);

//        if(user != null){
//
//            throw new NoSuchEmployeeException("There is no employee with ID = " +
//                    id + "in Database");
//
//
            userService.deleteUser(id);
            return "User with ID = " + id + " was deleted";

        }



    @PostMapping("/users/{userId}/friends")
    public ResponseEntity<?> addFriend(@PathVariable int userId, @RequestParam int friendId) {
        boolean result = userService.addFriend(userId, friendId);
        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/users/{userId1}/friends/{friendId2}")
    public ResponseEntity<?> deleteUserFriend(@PathVariable int userId1, @PathVariable int friendId2) {
        boolean success = userService.deleteUserFriend(userId1, friendId2);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
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
