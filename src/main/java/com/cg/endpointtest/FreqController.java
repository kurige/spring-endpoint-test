package com.cg.endpointtest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;

@Controller
public class FreqController {

    @Value("classpath:data/freq.txt")
    Resource freqDocument;

    @GetMapping("/freq")
    @ResponseBody
    public String frequency() {
        return readFrequencyData();
    }

    private String readFrequencyData() {
        try {
            return new String(Files.readAllBytes(freqDocument.getFile().toPath()));
        } catch (IOException e) {
            return String.format("Error reading frequency document: %s", e.getMessage());
        }
    }
}
