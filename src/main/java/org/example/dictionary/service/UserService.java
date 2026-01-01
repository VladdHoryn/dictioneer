package org.example.dictionary.service;

import lombok.Data;
import org.example.dictionary.model.User;
import org.example.dictionary.repository.DictionaryRepository;
import org.example.dictionary.repository.UserRepository;
import org.example.dictionary.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class UserService {
    private final UserRepository userRepository;

    public void addUser(User user){
        userRepository.save(user);
    }
    public void updateUser(Long userId, User user){
        User userToChange = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Uer with such ID was not found"));

        userToChange.setName(user.getName());
        userToChange.setPassword(user.getPassword());

        userRepository.save(userToChange);
    }
    public void deleteUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user); // CascadeType.ALL подбає про Dictionary + Word
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User getById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Boolean authorise(User user){
        List<User> users = userRepository.findAll();

        for(User currentUser : users){
            if(currentUser.getName().equals(user.getName()) && currentUser.getPassword().equals(user.getPassword()))
                return true;
        }
        return false;
    }

}
