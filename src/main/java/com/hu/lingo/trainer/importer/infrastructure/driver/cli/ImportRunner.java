package com.hu.lingo.trainer.importer.infrastructure.driver.cli;

import com.hu.lingo.trainer.importer.core.application.WordImporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ImportRunner implements CommandLineRunner {
     private final WordImporter wordImporter;

    public ImportRunner(WordImporter wordImporter) {
         this.wordImporter = wordImporter;
    }

    @Override
    public void run(String ...args) {
        log.info("Running word importer...");
        this.wordImporter.importWords();
        log.info("Words imported...");
    }
}
