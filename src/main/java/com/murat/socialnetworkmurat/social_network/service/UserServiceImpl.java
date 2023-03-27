package com.murat.socialnetworkmurat.social_network.service;

import com.murat.socialnetworkmurat.social_network.DAO.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.murat.socialnetworkmurat.social_network.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {



    @Autowired
    public UserRepository usersRepository;


    @Override
    public List<User> getAllUsers(){
        return  usersRepository.findAll();
    }

    @Override
    public User getUser(int id){

        User user = null;
        Optional<User> optional = usersRepository.findById(id);
        if (optional.isPresent()){
            user = optional.get();
        }
        return user;
    }

    @Override
    public void saveUser(User user){
        usersRepository.save(user);
    }




    @Override
    public void deleteUser(int id){
        usersRepository.deleteById(id);
    }


    @Override
    public List<User> getAllFriend(){
       return  usersRepository.findAll();
    }

    @Override
    public List<User> showFriendsPlus(int userId) {
        User user = usersRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        return new ArrayList<>(user.getFriends());
    }






    @Override
    public List<User> showFriends(int userId) {
        User user = usersRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        List<User> friends = new ArrayList<>();
        for (User friend : user.getFriends()) {
            friends.add(new User(friend.getId(), friend.getName()));
        }
        return friends;
    }

    @Override
    public List<Integer> showJustFriends(int userId) {
        User user = usersRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        List<Integer> friendIds = new ArrayList<>();
        for (User friend : user.getFriends()) {
            friendIds.add(friend.getId());
        }
        return friendIds;
    }

    @Override
    public boolean addFriend(int userId, int friendId) {
        User user = usersRepository.findById(userId).orElse(null);
        User friend = usersRepository.findById(friendId).orElse(null);
        if (user == null || friend == null) {
            return false;
        }
        user.getFriends().add(friend);
        usersRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteUserFriend(int userId1, int friendId2) {
        User user1 = usersRepository.findById(userId1).orElse(null);
        User user2 = usersRepository.findById(friendId2).orElse(null);
        if (user1 == null || user2 == null) {
            return false;
        }
        user1.getFriends().remove(user2);
        usersRepository.save(user1);
        return true;
    }
}



















