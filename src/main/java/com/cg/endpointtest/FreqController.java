package com.cg.endpointtest;

import com.cg.endpointtest.model.FreqItem;
import com.cg.endpointtest.model.FrequencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class FreqController {

    private final Logger logger = LoggerFactory.getLogger(FreqController.class);

    @Autowired
    FrequencyRepository repository;

    @GetMapping("/freq")
    @ResponseBody
    public ModelAndView displayFrequencyData(Map<String, Object> model) {

        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Order.desc("frequency")));
        Iterable<FreqItem> items = repository.findAll(pageable);
        model.put("freqItems", items);
        return new ModelAndView("freq.tmpl", model);
    }
}
