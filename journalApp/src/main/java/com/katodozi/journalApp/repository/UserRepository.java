package com.katodozi.journalApp.repository;

import com.katodozi.journalApp.entity.JournalEntry;
import com.katodozi.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//this repository/interface is responsible for dealing with the mongodb interface
//here the JournalEntry is the entity type and String is the type of primary key i.e. id
public interface UserRepository extends MongoRepository<User, ObjectId> {

    //since this inbuilt repository doesn't contain the find by username method we are creating it manually to use it
    //further in service and controller
    User findByUserName(String username);//find by the field name(username)
}
