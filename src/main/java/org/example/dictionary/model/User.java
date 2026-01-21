package org.example.dictionary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.KeyGenerator;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long userId;

    @NotBlank(message = "Name can not be null")
    protected String name;

    @NotBlank(message = "Email can not be null")
    @Email(message = "Email should be valid")
    protected String email;

    @NotBlank(message = "Password can not be null")
    protected String password;

    @NotNull
    protected UserRole role;

    public User(){
        this.role = UserRole.Guest;
    }

    public User(String name, String email, String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.name = name;
        this.email = email;
        this.password = passwordEncoder.encode(password);
        this.role = UserRole.User;
    }

    public void setPassword(String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.password = passwordEncoder.encode(password);
    }

    public void setRole(String newRole){
        switch (newRole){
            case "Guest": {
                this.role = UserRole.Guest;
                break;
            }
            case "User": {
                this.role = UserRole.User;
                break;
            }
            case "Operator": {
                this.role = UserRole.Operator;
                break;
            }
            case "Administrator": {
                this.role = UserRole.Administrator;
                break;
            }
            default:
                this.role = UserRole.Guest;
        }
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

enum UserRole{
    Guest,
    User,
    Operator,
    Administrator
}
