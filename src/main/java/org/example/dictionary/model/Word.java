package org.example.dictionary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wordId;

    @ManyToOne
    @JoinColumn(name = "dictionary_id")
    @JsonIgnore
    private Dictionary dictionary;

    private String word;
    private String translation;
    private String grammaticalCategory;
    private String description;
    private String complexity;
}
