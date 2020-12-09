package com.hu.lingo.trainer.importer.infrastructure.driver.cli;

import com.hu.lingo.trainer.importer.core.application.FileService;
import com.hu.lingo.trainer.importer.core.application.WordImporter;
import com.hu.lingo.trainer.importer.core.domain.entity.Checksum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Component
public class ImportRunner implements CommandLineRunner {
     private final WordImporter wordImporter;
     private final FileService fileService;

    public ImportRunner(WordImporter wordImporter, FileService fileService) {
         this.wordImporter = wordImporter;
         this.fileService = fileService;
    }

    @Override
    public void run(String ...args) throws IOException, NoSuchAlgorithmException {
        log.info("Starting word importer...");
        this.wordImporter.importWords();
        this.databaseRunner();
        log.info("Word importer completed...");

    }

    public void databaseRunner() throws IOException, NoSuchAlgorithmException {
        Checksum checksum = this.fileService.generateHash();
        boolean currentChecksumExists = false;

        boolean oneExists = this.fileService.findAll().size() != 0;
        if (oneExists) currentChecksumExists = this.fileService.findChecksum(checksum);

        if (!oneExists) {
            log.info("No hash of file found in database, which means this is the first time. Adding hash to database...");
            this.fileService.save(checksum);
        }

        if (oneExists && currentChecksumExists) {
            log.info("File has not been changed, skipping database update...");
        } else {
            log.info("File has been changed, updating hash in database...");

            Checksum existingChecksum = this.fileService.findAll().get(0);
            existingChecksum.setHash(checksum.getHash());

            this.fileService.update(existingChecksum);
        }
    }
}
