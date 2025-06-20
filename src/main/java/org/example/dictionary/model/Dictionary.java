package org.example.dictionary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dictionaryId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    private String name;
    private String sourceLanguage;
    private String targetLanguage;
    private String description;

    @OneToMany(mappedBy = "user",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    List<Word> words;

    public void addWord(Word word){
        words.add(word);
        word.setDictionary(this);
    }

    public void removeWord(Word word){
        words.remove(word);
        word.setDictionary(null);
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if( o == null || getClass() != o.getClass()) {
            return false;
        }

        Dictionary dictionary = (Dictionary) o;
        return dictionaryId == dictionary.dictionaryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dictionaryId);
    }
}
