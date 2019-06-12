package com.cg.endpointtest;

import com.cg.endpointtest.model.FreqList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class FreqController {

    private final Logger logger = LoggerFactory.getLogger(FreqController.class);

    @Autowired
    FreqList freqList;

    @GetMapping("/freq")
    @ResponseBody
    public ModelAndView displayFrequencyData(Map<String, Object> model) {

        model.put("freqItems", freqList.getItems());
        return new ModelAndView("freq.tmpl", model);
    }
}
