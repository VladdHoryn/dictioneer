package org.example.dictionary.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String name;
    private String password;

    @OneToMany(mappedBy = "user",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    List<Dictionary> dictionaries = new ArrayList<>();

    public void addUser(Dictionary dictionary){
        dictionaries.add(dictionary);
        dictionary.setUser(this);
    }

    public void removeUser(Dictionary dictionary){
        dictionaries.remove(dictionary);
        dictionary.setUser(null);
    }
}
