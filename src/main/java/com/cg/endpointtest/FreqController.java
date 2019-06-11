package com.cg.endpointtest;

import com.cg.endpointtest.model.FreqItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class FreqController {

    @Value("classpath:data/freq.txt")
    Resource freqDocument;

    @GetMapping("/freq")
    @ResponseBody
    public ModelAndView displayFrequencyData(Map<String, Object> model) {
        List items = new LinkedList();
        items.add(new FreqItem("hello", 2, 0.5));
        items.add(new FreqItem("world", 2, 0.5));

        model.put("freqItems", items);
        return new ModelAndView("freq.tmpl", model);
    }

    private String readFrequencyData() {
        try {
            return new String(Files.readAllBytes(freqDocument.getFile().toPath()));
        } catch (IOException e) {
            return String.format("Error reading frequency document: %s", e.getMessage());
        }
    }
}
