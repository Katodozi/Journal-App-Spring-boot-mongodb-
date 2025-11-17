package com.katodozi.journalApp.repository;

import com.katodozi.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//this repository/interface is responsible for dealing with the mongodb interface
//here the JournalEntry is the entity type and String is the type of primary key i.e. id
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}
