package com.cas.filemonitor.writer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RandomFileWriter {

    private static final Logger log = LoggerFactory.getLogger(RandomFileWriter.class);

    private final String FILE_NAME_PATTERN = "YYYYMMddHHmm";

    // Write to a random file, keeping in classpath(instead of S3) and returns file reference
    public String writeToFile() throws IOException  {

        String fileName = LocalDateTime
                .now()
                .format(DateTimeFormatter.ofPattern(FILE_NAME_PATTERN))
                .concat(".txt");

        log.info("--- Writing into random file {} ---", fileName);

        File file = new File(fileName);
        file.createNewFile();
        file.setWritable(Boolean.TRUE);

        String word = "";
        String randomWord = "";

        for (int fileLength = 1; fileLength <= 5000; fileLength += randomWord.length()) {   // keeping file length to 5k for simplicity
            randomWord = RandomStringUtils
                    .randomAlphanumeric(7, 20)
                    .concat(" ")
                    .concat("CDS");
            randomWord = (fileLength % 10) == 0 ? randomWord.concat("\n") : randomWord.concat(" ");
            word = word
                    .concat(randomWord);
        }

        FileUtils.write(file, word, Charset.defaultCharset());

        log.info("--- End of writing into random file {} ---", fileName);

        return fileName;
    }
}
