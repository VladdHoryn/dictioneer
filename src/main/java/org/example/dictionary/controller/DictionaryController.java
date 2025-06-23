package org.example.dictionary.controller;

import org.springframework.ui.Model;
import org.example.dictionary.model.Dictionary;
import org.example.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class DictionaryController {
    private final DictionaryService dictionaryService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService){
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/allDictionaries")
    public String getDictionaries(Model model){
        model.addAttribute("dictionaryList", dictionaryService.getAll());

        return "dictionaryGet.html";
    }

    @PostMapping("/addDictionary/{userId}")
    public ResponseEntity<String> addDictionary(@RequestBody Dictionary dictionary, @PathVariable Long userId){
        dictionaryService.addDictionaryToUser(userId, dictionary);

        return ResponseEntity.ok("Dictionary added");
    }

}
