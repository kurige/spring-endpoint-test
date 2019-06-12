package com.cg.endpointtest.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class FreqList extends LinkedList<FreqItem> {

    private static final Logger logger = LoggerFactory.getLogger(FreqList.class);

    public static FreqList parseFile(File csvFile) {
        FreqList retval = new FreqList();
        try (Scanner scanner = new Scanner(csvFile)) {
            while (scanner.hasNextLine()) {
                try (Scanner rowScanner = new Scanner(scanner.nextLine())) {
                    rowScanner.useDelimiter(",");
                    String word = rowScanner.next();
                    int count = rowScanner.nextInt();
                    double freq = rowScanner.nextDouble();

                    retval.add(new FreqItem(word, count, freq));
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("Failed to find CSV file: {}", csvFile.toPath());
        }

        return retval;
    }
}
