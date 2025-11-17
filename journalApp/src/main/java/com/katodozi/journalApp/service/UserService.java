package com.katodozi.journalApp.service;

import com.katodozi.journalApp.entity.JournalEntry;
import com.katodozi.journalApp.entity.User;
import com.katodozi.journalApp.repository.JournalEntryRepository;
import com.katodozi.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user){

            userRepository.save(user);

    }

    public List<User> getAll(){
        return userRepository.findAll();//journalEntryRepository is a bean(object)
    }
    //the id we enter might or might not have a corresponding entry so it is kept as optional
    public Optional<User> findById(ObjectId id){

        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){

        userRepository.deleteById(id);
    }

    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }
}
