package org.example.dictionary.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.KeyGenerator;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long userId;

    protected String name;
    protected String email;
    protected String password;

    User(String name, String email, String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.name = name;
        this.email = email;
        this.password = passwordEncoder.encode(password);
    }

    public void setPassword(String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.password = passwordEncoder.encode(password);
    }

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
