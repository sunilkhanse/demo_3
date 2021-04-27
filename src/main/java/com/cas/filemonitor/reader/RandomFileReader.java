package com.cas.filemonitor.reader;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RandomFileReader {

    private static final Logger log = LoggerFactory.getLogger(RandomFileReader.class);

    // Read file from memory and check if line contains keyword
    public void readFile(String fileName, final String keyword) throws IOException {

        log.info("--- Reading from file {} ---", fileName);

        Map<String, Integer> wordCountMap = new HashMap<>();

        List<String> fileContent = FileUtils
                .readLines(new File(fileName), Charset.defaultCharset())
                ;

        fileContent.removeIf(notMatch -> !notMatch.contains(keyword)); // Removing unwanted lines from file content

        fileContent
                .forEach(line -> {
                    String [] words = line.split(" ");
                    Arrays.asList(words)
                            .forEach(word -> {
                                if (!wordCountMap.containsKey(word)) {
                                    wordCountMap.put(word, 0);
                                }
                                Integer wordCount = wordCountMap.get(word);
                                wordCount = wordCount + 1;
                                wordCountMap.put(word, wordCount);
                            });
                });

        log.info("--- End of reading from file {} ---", fileName);

        log.debug("---- Keyword {} search count {} in file {} ---"
                , new Object[]{keyword, wordCountMap.get(keyword), fileName});   // Write the keyword search could to log file
    }

}