package com.hu.lingo.trainer.importer.infrastructure.driver.cli;

import com.hu.lingo.trainer.importer.core.application.FileService;
import com.hu.lingo.trainer.importer.core.application.WordImporter;
import com.hu.lingo.trainer.importer.core.domain.entity.Checksum;
import com.hu.lingo.trainer.importer.infrastructure.driver.DatabaseRunner;
import com.hu.lingo.trainer.importer.infrastructure.driver.TxtFileRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Component
public class ImportRunner implements CommandLineRunner, DatabaseRunner, TxtFileRunner {
     private final WordImporter wordImporter;
     private final FileService fileService;

    public ImportRunner(WordImporter wordImporter, FileService fileService) {
         this.wordImporter = wordImporter;
         this.fileService = fileService;
    }

    /** When starting the SpringApplication, this method will be called. */
    @Override
    public void run(String ...args) throws IOException, NoSuchAlgorithmException {
        log.info("Starting word importer...");
        this.txtFileRunner();
        this.databaseRunner();
        log.info("Word importer completed...");
    }

    /** Importing words from the source text file, with a certain amount (50 words) */
    @Override
    public void txtFileRunner() {
        this.wordImporter.importWords(50);
    }

    /** Writing the filtered words to the database, ONLY if the source text file's hash has been changed.
     *  If the file's hash has not been changed, NOTHING will be called on the database.
     *  If the file's hash has been changed, all words in the database will be removed and the new words will be saved in the database,
     *  and the hash of the source text fill will be updated inside the database. */
    @Override
    public void databaseRunner() throws NoSuchAlgorithmException {
        Checksum checksum = this.fileService.generateHash();

        boolean currentChecksumExists = false;
        Checksum existingChecksum = new Checksum();

        boolean oneExists = !this.fileService.findAll().isEmpty();
        if (oneExists) {
            currentChecksumExists = this.fileService.findChecksum(checksum);
            existingChecksum = this.fileService.findAll().get(0);
        }

        if (!oneExists) {
            log.info("No hash of file found in database, which means this is the first time. Adding hash to database...");
            this.fileService.save(checksum);

            existingChecksum = this.fileService.findAll().get(0);
            this.wordImporter.importWordsToDatabase();
        }

        if (checksum.getHash().equals(existingChecksum.getHash())) {
            log.info("File has not been changed, skipping database update...");
        } else if (!checksum.getHash().equals(existingChecksum.getHash())){
            log.info("File has been changed, updating database...");

            existingChecksum.setHash(checksum.getHash());

            this.fileService.update(existingChecksum);
            this.wordImporter.importWordsToDatabase();
        }
    }
}
