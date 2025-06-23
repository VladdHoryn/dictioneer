package org.example.dictionary.controller;

import org.example.dictionary.model.Word;
import org.example.dictionary.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WordController {
    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService){
        this.wordService = wordService;
    }

    @GetMapping("/allWords")
    public String getWords(Model model){
        model.addAttribute("wordList", wordService.getAll());

        return "wordGet.html";
    }

    @PostMapping("/addWord/{dictionaryId}")
    public ResponseEntity<String> addWord(@RequestBody Word word, @PathVariable Long dictionaryId){
        wordService.addWordToDictionary(dictionaryId, word);

        return ResponseEntity.ok("Word added");
    }
}
