package com.manager.tab.tabmanager.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class PythonTokenizerServiceImpl implements PythonTokenizerService {
    private static final String SCRIPT_PATH = "src/main/resources/scripts/tokenizer.py";
    private static final String PYTHON_EXE =
            Paths.get("src/main/resources/scripts/.venv/Scripts/python.exe")
                    .toAbsolutePath().toString();

    private String flattenList(List<String> list) {
        return String.join("\n", list);
    }

    @Override
    public String tokenize(String text, String language) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(PYTHON_EXE, SCRIPT_PATH, language);
        pb.environment().put("PYTHONIOENCODING", "utf-8");
        pb.environment().put("PYTHONUTF8", "1");
        Process process = pb.start();

        try (OutputStream os = process.getOutputStream()) {
            os.write(text.getBytes(StandardCharsets.UTF_8));
        }

        List<String> output;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            output = br.lines().collect(Collectors.toList());
        }

        boolean finished = process.waitFor(10, TimeUnit.SECONDS);
        if (!finished) {
            process.destroyForcibly();
            throw new IOException("Python tokenizer timed out");
        }

        if (process.exitValue() != 0) {
            String error;
            try (InputStream es = process.getErrorStream()) {
                error = new String(es.readAllBytes(), StandardCharsets.UTF_8);
            }
            throw new IOException("Python tokenizer failed: " + error);
        }

        return flattenList(output);
    }
}
