package org.example.dictionary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long dictionaryId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @NotEmpty(message = "Dictionary required to have user")
    protected User user;

    @NotBlank(message = "Name can not be null")
    @Size(max = 30, message = "Name can not be over 30 symbols")
    protected String name;
    @NotBlank(message = "Source language can not be null")
    protected String sourceLanguage;
    @NotBlank(message = "Target language can not be null")
    protected String targetLanguage;
    @Size(min = 0, max = 200, message = "description can not be over 200 symbols")
    protected String description;

    @OneToMany(mappedBy = "dictionary",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    protected List<Word> words = new ArrayList<>();

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

    public Long getUserId(){
        return this.user.getUserId();
    }
}
