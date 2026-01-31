package org.example.dictionary.serviceTest;

import org.example.dictionary.model.Dictionary;
import org.example.dictionary.model.Word;
import org.example.dictionary.repository.DictionaryRepository;
import org.example.dictionary.repository.UserRepository;
import org.example.dictionary.repository.WordRepository;
import org.example.dictionary.service.WordService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class wordServiceTest {

    @InjectMocks
    WordService wordService;

    @Mock
    WordRepository wordRepository;

    @Mock
    DictionaryRepository dictionaryRepository;

    @Mock
    UserRepository userRepository;

    @Test
    void addWordToDictionary_shouldSaveWord(){
        long dictionaryId = 1L;
        Dictionary dictionary = new Dictionary();
        Word word = new Word();

        when(dictionaryRepository.findById(dictionaryId))
                .thenReturn(Optional.of(dictionary));

        wordService.addWordToDictionary(dictionaryId, word);

        verify(wordRepository).save(word);
    }

    @Test
    void updateWordTest_shouldUpdate(){

    }
}
