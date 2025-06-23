package org.example.dictionary.service;

import lombok.Data;
import org.example.dictionary.model.Dictionary;
import org.example.dictionary.model.User;
import org.example.dictionary.repository.DictionaryRepository;
import org.example.dictionary.repository.UserRepository;
import org.example.dictionary.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class DictionaryService {
    private final UserRepository userRepository;
    private final DictionaryRepository dictionaryRepository;

    public Dictionary addDictionaryToUser(Long userId, Dictionary dictionary){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        user.addDictionary(dictionary);

        return dictionaryRepository.save(dictionary);
    }
    public void deleteDictionary(Dictionary dictionary) {
        for(var word : dictionary.getWords()){
            dictionary.removeWord(word);
        }
        dictionaryRepository.delete(dictionary);
    }
    public void deleteDictionaryById(Long dictionaryId) {
        Dictionary dictionary = dictionaryRepository.findById(dictionaryId)
                .orElseThrow(() -> new RuntimeException("Dictionary not found"));

        dictionaryRepository.delete(dictionary);
    }

    public List<Dictionary> getAll(){
        return dictionaryRepository.findAll();
    }

    public Dictionary getById(Long dictionaryId){
        return dictionaryRepository.findById(dictionaryId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
