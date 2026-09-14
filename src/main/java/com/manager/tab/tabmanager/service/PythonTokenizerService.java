package com.manager.tab.tabmanager.service;

import java.io.IOException;

public interface PythonTokenizerService {
    public String tokenize(String text, String language) throws IOException, InterruptedException;
}
