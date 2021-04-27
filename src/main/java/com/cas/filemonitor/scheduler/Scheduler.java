package com.cas.filemonitor.scheduler;

import com.cas.filemonitor.reader.RandomFileReader;
import com.cas.filemonitor.writer.RandomFileWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Scheduler {

    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private RandomFileWriter randomFileWriter;

    @Autowired
    private RandomFileReader randomFileReader;

    private List<String> prevFileName = new ArrayList<>(2);

    /**
     * Write to a random file at every hour 5 & 10 mins. It will generate 2 files
     * */
    @Scheduled(cron = "0 5,10 * * * *")
    public void writeIntoFile() {
        try {
            String fileName = this.randomFileWriter.writeToFile();
            this.prevFileName.add(fileName);
        } catch (Exception e) {
            log.error("--- Exception while writing into random file ---", e);
        }
    }

    /**
     * Search keyword from random generated file at every hour 15 mins and read keyword from both files
     * */
    @Scheduled(cron = "0 15 * * * *")
    public void readingFromFile() {
        prevFileName.forEach(file -> {
            try {
                this.randomFileReader.readFile(file, "CDS");
            } catch (Exception e) {
                log.error("--- Exception while reading from file {} ---",
                        new Object[]{file, e});
            }
        });
        this.prevFileName.clear();  // clears the file names
    }

}
