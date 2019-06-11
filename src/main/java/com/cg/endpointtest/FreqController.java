package com.cg.endpointtest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FreqController {

    @GetMapping("/freq")
    @ResponseBody
    public String frequency() {
        return "Hello World";
    }
}
