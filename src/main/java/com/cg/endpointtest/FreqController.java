package com.cg.endpointtest;

import com.cg.endpointtest.model.FreqList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@Controller
public class FreqController {

    private final Logger logger = LoggerFactory.getLogger(FreqController.class);

    @Value("classpath:data/freq.txt")
    Resource freqDocument;

    @GetMapping("/freq")
    @ResponseBody
    public ModelAndView displayFrequencyData(Map<String, Object> model) {

        FreqList items = new FreqList();
        try {
            items = FreqList.parseFile(freqDocument.getFile());
        } catch (IOException e) {
            logger.error("Error while loading frequency data: {}", e.getMessage());
        }
        model.put("freqItems", items);
        return new ModelAndView("freq.tmpl", model);
    }
}
