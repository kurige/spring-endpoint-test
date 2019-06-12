package com.cg.endpointtest;

import com.cg.endpointtest.model.FreqItem;
import com.cg.endpointtest.model.FrequencyRepository;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Collections;
import java.util.List;

@Component
public class DatabaseInitializer {

    Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

    @Autowired
    FrequencyRepository frequencyRepository;

    @PostConstruct
    private void setupData() {
        logger.info("Initializing database...");
        setupWordData();
        logger.info("Finished initializing database.");
    }

    private void setupWordData() {
        List<FreqItem> words = getWords();
        for (FreqItem item : words) {
            if (frequencyRepository.findByWord(item.getWord()) == null) {
                frequencyRepository.save(item);
            }
        }
    }

    private List<FreqItem> getWords() {
        return loadObjectList(FreqItem.class, "data/freq.txt");
    }

    private <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<T> readValues = mapper.reader(type).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            logger.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }
}
