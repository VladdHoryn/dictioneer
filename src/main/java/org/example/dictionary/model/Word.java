package org.example.dictionary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    protected Dictionary dictionary;

    protected String word;
    protected String translation;
    protected String grammaticalCategory;
    protected String description;
    protected String complexity;
}
