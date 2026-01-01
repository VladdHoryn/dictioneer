package org.example.dictionary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotEmpty(message = "User Id can not be null")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long userId;

    @NotBlank(message = "Name can not be null")
    protected String name;

    @NotBlank(message = "Email can not be null")
    @Email(message = "Email should be valid")
    protected String email;

    @NotBlank(message = "Password can not be null")
    @Size(min = 8, message = "Password has to be maro than 8 symbols")
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
