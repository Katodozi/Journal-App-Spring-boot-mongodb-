package com.katodozi.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection="users")
//@Getter
//@Setter
@Data
@NoArgsConstructor
public class User {

    @Id
    private ObjectId id;
    @Indexed(unique = true)//for this annotation to work you have to include auto index properties on application.properties
    @NonNull
    private String userName;
    @NonNull
    private String password;

    @DBRef //we must use this annotation to let the jvm know that the list we created will keep the reference of entries in journal_entries
    private List<JournalEntry> journalEntries = new ArrayList<>(); // List of Journal Entries
}
