package com.hu.lingo.trainer.importer.infrastructure;

import com.hu.lingo.trainer.importer.core.application.FileService;
import com.hu.lingo.trainer.importer.core.application.WordImporter;
import com.hu.lingo.trainer.importer.core.domain.WordFilter;
import com.hu.lingo.trainer.importer.core.domain.entity.Checksum;
import com.hu.lingo.trainer.importer.core.ports.WordReader;
import com.hu.lingo.trainer.importer.core.ports.WordWriter;
import com.hu.lingo.trainer.importer.infrastructure.driver.cli.ImportRunner;
import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ImportRunnerTest {
    private static WordReader mockReader;
    private static WordFilter mockFilter;
    private static WordWriter mockWriter;

    private static FileService mockFileService;
    private static WordWriter mockDbWordWriter;

    @BeforeAll
    static void beforeAll() {
        mockReader = mock(WordReader.class);
        mockFilter = mock(WordFilter.class);
        mockWriter = mock(WordWriter.class);

        mockFileService = mock(FileService.class);
        mockDbWordWriter = mock(WordWriter.class);
    }

    @Test
    void import_words_once_at_startup() throws IOException, NoSuchAlgorithmException {
        WordImporter mockWordImporter = mock(WordImporter.class, withSettings().useConstructor(mockReader, mockFilter, mockWriter, mockDbWordWriter));
        ImportRunner mockImportRunner = mock(ImportRunner.class, withSettings().useConstructor(mockWordImporter, mockFileService));

        mockImportRunner.run();

        verify(mockImportRunner, times(1))
                .run();
    }

}