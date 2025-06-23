package org.example.dictionary.service;

import lombok.Data;
import org.example.dictionary.model.Dictionary;
import org.example.dictionary.model.User;
import org.example.dictionary.model.Word;
import org.example.dictionary.repository.DictionaryRepository;
import org.example.dictionary.repository.UserRepository;
import org.example.dictionary.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class WordService {
    private final UserRepository userRepository;
    private final DictionaryRepository dictionaryRepository;
    private final WordRepository wordRepository;

    public Word addWordToDictionary(Long dictionaryId, Word word){
        Dictionary dictionary = dictionaryRepository.findById(dictionaryId)
                .orElseThrow(() -> new RuntimeException("Word not found"));

        dictionary.addWord(word);

        return wordRepository.save(word);
    }
    public void deleteWord(Word word){
        wordRepository.delete(word);
    }
    public void deleteWordById(Long wordId){
        Word word = wordRepository.findById(wordId)
                .orElseThrow(() -> new RuntimeException("Word not found"));

        wordRepository.delete(word);
    }
    public List<Word> getAll(){
        return wordRepository.findAll();
    }

    public Word getById(Long wordId){
        return wordRepository.findById(wordId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
