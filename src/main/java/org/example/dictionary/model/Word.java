package org.example.dictionary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Access(AccessType.FIELD)
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long wordId;

    @ManyToOne
    @JoinColumn(name = "dictionary_id")
    @JsonIgnore
    @NotNull(message = "Word required to have dictionary")
    protected Dictionary dictionary;

    protected String word;
    protected String translation;
    protected String grammaticalCategory;
    protected String complexity;

    public Word(Dictionary dictionary,
                String word,
                String translation,
                String grammaticalCategory,
                String complexity) {

        this.dictionary = dictionary;
        this.word = word;
        this.translation = translation;
        this.grammaticalCategory = grammaticalCategory;
        this.complexity = complexity;
    }
}
