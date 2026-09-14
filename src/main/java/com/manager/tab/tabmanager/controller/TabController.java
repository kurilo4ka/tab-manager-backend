package com.manager.tab.tabmanager.controller;

import com.manager.tab.tabmanager.service.PythonTokenizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TabController {
    @Autowired
    private PythonTokenizerService pythonTokenizerService;

    @PostMapping(value = "/tabs/tokenize", consumes = MediaType.TEXT_PLAIN_VALUE)
    public String tokenize(@RequestBody String text, @RequestParam(required = true) String language) {
        String parsed;
        try {
            parsed = pythonTokenizerService.tokenize(text, language);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return parsed;
    }
}
