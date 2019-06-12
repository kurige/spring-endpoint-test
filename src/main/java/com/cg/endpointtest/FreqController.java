package com.cg.endpointtest;

import com.cg.endpointtest.model.FreqItem;
import com.cg.endpointtest.model.FrequencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Controller
public class FreqController {

    private final Logger logger = LoggerFactory.getLogger(FreqController.class);

    @Autowired
    FrequencyRepository repository;

    @GetMapping("/freq")
    public ModelAndView displayFrequencyData(Pageable pageable) {

        UriComponentsBuilder uriComponentsBuilder = getUriComponentsWithPageable(pageable);

        Map<String, Object> model = new HashMap<>();

        Page<FreqItem> words = repository.findAll(pageable);
        model.put("words", words);

        Integer previousPageNum = pageable.previousOrFirst().getPageNumber();
        String previous = pageUriString(uriComponentsBuilder, previousPageNum);
        model.put("previous", previous);

        Integer nextPageNum = pageable.next().getPageNumber();
        String next = pageUriString(uriComponentsBuilder, nextPageNum);
        model.put("next", next);

        return new ModelAndView("freq.tmpl", model);
    }

    // Simply adding a UriComponentsBuilder to the method signature doesn't seem to populate the query parameters.
    // Need to mix Pageable related query parameters (ex. 'page', 'size', 'sort') back into the request to be able to
    // build out the `next` and `previous` page links without losing sort order.
    private UriComponentsBuilder getUriComponentsWithPageable(Pageable pageable) {
        UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        builder.replaceQueryParam("page", pageable.getPageNumber());

        if (pageable.getSort() != Sort.unsorted()) {
            String sort = pageable.getSort().toString().replaceAll(": ", ",");
            builder.replaceQueryParam("sort", sort);
        }

        return builder;
    }

    private String pageUriString(UriComponentsBuilder uriComponentsBuilder, Integer pageNum) {
        String pageUriString = uriComponentsBuilder
                .cloneBuilder()
                .replaceQueryParam("page", pageNum)
                .encode()
                .build()
                .toUriString();
        return pageUriString;
    }
}
