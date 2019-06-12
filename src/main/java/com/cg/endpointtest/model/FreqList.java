package com.cg.endpointtest.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Component
public class FreqList {

    private final Logger logger = LoggerFactory.getLogger(FreqList.class);

    private List<FreqItem> items;

    @Autowired
    public FreqList(@Value("classpath:data/freq.txt") Resource freqDocument) {
        items = new LinkedList<>();

        try {
            parseFile(freqDocument.getFile());
        } catch (IOException e) {
            logger.error("Error while loading frequency data: {}", e.getMessage());
        }
    }

    public void parseFile(File csvFile) {
        logger.debug("Parsing CSV file: {}", csvFile.getName());
        try (Scanner scanner = new Scanner(csvFile)) {
            while (scanner.hasNextLine()) {
                try (Scanner rowScanner = new Scanner(scanner.nextLine())) {
                    rowScanner.useDelimiter(",");
                    String word = rowScanner.next();
                    int count = rowScanner.nextInt();
                    double freq = rowScanner.nextDouble();

                    items.add(new FreqItem(word, count, freq));
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("Failed to find CSV file: {}", csvFile.toPath());
        }
    }

    public List<FreqItem> getItems() {
        return items;
    }
}
