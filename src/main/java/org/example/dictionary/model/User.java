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
    protected long userId;

    protected String name;
    protected String email;
    protected String password;

    @OneToMany(mappedBy = "user",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    List<Dictionary> dictionaries = new ArrayList<>();

    public void addDictionary(Dictionary dictionary){
        dictionaries.add(dictionary);
        dictionary.setUser(this);
    }

    public void removeDictionary(Dictionary dictionary){
        dictionaries.remove(dictionary);
        dictionary.setUser(null);
    }
}
