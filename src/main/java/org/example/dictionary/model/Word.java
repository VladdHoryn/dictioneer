package org.example.dictionary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long wordId;

    @ManyToOne
    @JoinColumn(name = "dictionary_id")
    @JsonIgnore
    @NotEmpty(message = "Word required to have dictionary")
    protected Dictionary dictionary;

    protected String word;
    protected String translation;
    protected String grammaticalCategory;
    protected String complexity;
}
