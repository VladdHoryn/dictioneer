package org.example.dictionary.service;

import lombok.Data;
import org.example.dictionary.model.User;
import org.example.dictionary.repository.DictionaryRepository;
import org.example.dictionary.repository.UserRepository;
import org.example.dictionary.repository.WordRepository;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserService {
    private final UserRepository userRepository;

    public void addUser(User user){
        userRepository.save(user);
    }
    public void deleteUser(User user) {
        for(var dictionary : user.getDictionaries()){
            user.removeDictionary(dictionary);
        }
        userRepository.delete(user);
    }
    public void deleteUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user); // CascadeType.ALL подбає про Dictionary + Word
    }

}
