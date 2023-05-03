package com.murat.socialnetworkmurat.social_network.service;

import com.murat.socialnetworkmurat.social_network.DAO.UserRepository;
import com.murat.socialnetworkmurat.social_network.exceptionHandling.NoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.murat.socialnetworkmurat.social_network.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());


    @Autowired
    public UserRepository usersRepository;


    /**
     * Returns a list of all users from the database
     *
     * @throws NoSuchUserException if the user list is empty
     * @return a list of all users
     */
    @Override
    public List<User> getAllUsers(){

        return  usersRepository.findAll();
    }

    /**
     * Returns the user object corresponding to the given user ID
     *
     * @param id the user ID to retrieve
     * @return the user object corresponding to the given ID
     * @throws NoSuchUserException if no user exists with the given ID
     */
    @Override
    public Optional<User> getUser(int id) {

        Optional<User> optional = usersRepository.findById(id);
        optional.get().getId();
        User user = optional.get();
        log.info("Retrieved user with = " + id);
        return optional;
    }



    /**
     * Saves the given user to the database
     *
     * @throws NoSuchUserException if the user data is invalid or cannot be saved
     * @param user the user object to save
     */
    @Override
    public void saveUser(User user){
        try {
            usersRepository.save(user);
//            log.info("User who id  = " + user.getId() + "has been created");
        } catch (Exception e) {
            throw new NoSuchUserException("Please check the data your entered" );
        }
//        log.info("User with id = " + user.getId() + "has been saved");
    }



    /**
     * Deletes the user with the given ID from the database
     *
     * @throws NoSuchUserException if no user exists with the given ID
     * @param id the user ID to delete
     */
    @Override
    public void deleteUser(int id){
        if(!usersRepository.existsById(id)){
            throw new NoSuchUserException( "User with id " + id + " not found");
        }
        usersRepository.deleteById(id);
        log.info("User " + id + "was deleted") ;
    }



    /**
     * Retrieves a list of the user's friends with the given ID.
     *
     * @param userId the ID of the user to retrieve friends for
     * @return a list of the user's friends
     */
    @Override
    public Optional<Object> showFriends(int userId) {
        Optional<User> user = usersRepository.findById(userId);
        if (!user.isPresent()) {
            return Optional.empty();
        }
        List<User> friends = new ArrayList<>();
        for (User friend : user.get().getFriends()) {
            friends.add(new User(friend.getId(), friend.getName()));
        }
        log.info("Retrieved user id " + userId + " and his friends" ) ;

        return Optional.of(friends);
    }

    /**
     * Retrieves a list of the IDs of the user's friends with the given ID.
     *
     * @param userId the ID of the user to retrieve friend IDs for
     * @return a list of the user's friend IDs
     * @throws NoSuchUserException if no user exists with the given ID
     */
    public List<Integer> showJustFriends(int userId) {
        Optional<User> userOptional = usersRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new NoSuchUserException( "User with id " + userId + " not found");
        }
        User user = userOptional.get();
        List<Integer> friendIds = new ArrayList<>();
        for (User friend : user.getFriends()) {
            friendIds.add(friend.getId());
        }
        log.info("User with id " + userId  + "and his friends");
        return friendIds;
    }



    /**
     * Adds a friend to the user's friend list.
     *
     * @param userId the ID of the user to add a friend for
     * @param friendId the ID of the friend to add
     * @return true if the friend was added successfully, false otherwise
     * @throws NoSuchUserException if either user with the given ID does not exist
     */
    @Override
    public boolean addFriend(int userId, int friendId) {
        User user = usersRepository.findById(userId).orElse(null);
        User friend = usersRepository.findById(friendId).orElse(null);
        if (user == null || friend == null) {
            return false;
        }
        if (user.getFriends().contains(friend)) {
            throw new NoSuchUserException("Field already exists ");
        }
        if (userId==friendId){
            throw new NoSuchUserException("You can't add yourself as a friend ");
        }
        user.getFriends().add(friend);
        usersRepository.save(user);
        log.info("User with ID " + userId + "add friend with ID " + friendId);
        return true;
    }


    /**
     * Removes a friend from the user's friend list.
     *
     * @param userId the ID of the user to remove a friend for
     * @param friendId the ID of the friend to remove
     * @return true if the friend was removed successfully, false otherwise
     * @throws NoSuchUserException if either user with the given ID does not exist
     */
    @Override
    public boolean deleteUserFriend(int userId, int friendId) {
        Optional<User> user1 = usersRepository.findById(userId);
        Optional <User> user2 = usersRepository.findById(friendId);
        if (user1 == null || user2 == null) {
            throw new NoSuchUserException( "Friendship between user with ID " + userId + " and friend with ID " + friendId );
        }
        user1.get().getFriends().remove(user2);
        usersRepository.save(user1.get());
        log.info("Friendship between user with ID " + userId + "and friend with ID " + friendId);
        return true;
    }


}



















