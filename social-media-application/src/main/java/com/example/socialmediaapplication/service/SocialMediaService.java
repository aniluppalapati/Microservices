package com.example.socialmediaapplication.service;

import com.example.socialmediaapplication.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class SocialMediaService {
    public static List<User> users = new ArrayList<>();
    static int count=0;

    static {
        users.add(new User(++count,"Anil", LocalDate.now().minusYears(30)));
        users.add(new User(++count,"Kumar", LocalDate.now().minusYears(3)));
        users.add(new User(++count,"Ram", LocalDate.now().minusYears(25)));
        users.add(new User(++count,"Venkatesh", LocalDate.now().minusYears(20)));
    }

    public User createUser(User user){
        user.setId(++count);
        users.add(user);
        return user;
    }

    public List<User> fildAllUsers(){
        return users;
    }

    public User findById(int id){
           for(User user:users)
               if(user.getId()==id)
                   return user;
        return null;
    }
    public List<User> removeUser(int id){
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove(); // Use iterator's remove method to safely remove the element
            }
        }
        return users;
    }
}
