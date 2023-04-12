package com.murat.socialnetworkmurat.social_network.service;

import com.murat.socialnetworkmurat.social_network.entity.User;

import java.util.List;

public interface UserService {


    public List<User> getAllUsers();

    public void saveUser (User user);

    public User getUser(int id);

    public void deleteUser(int id);
    





    public List<User> showFriends(int userId);






    List<Integer> showJustFriends(int userId);

    boolean addFriend(int userId, int friendId);

//    boolean removeFriend(int userId, int friendId);


    boolean deleteUserFriend(int userId1, int friendId2);
}
