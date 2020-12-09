package com.hu.lingo.trainer.importer.infrastructure.driver.cli;

import com.hu.lingo.trainer.importer.core.application.WordImporter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ImportRunner implements CommandLineRunner {
     private final WordImporter wordImporter;

    public ImportRunner(WordImporter wordImporter) {
         this.wordImporter = wordImporter;
    }

    @Override
    public void run(String ...args) {
        System.out.println("Running Word importer...");
        this.wordImporter.importWords();
        System.out.println("Words imported...");
    }
}
