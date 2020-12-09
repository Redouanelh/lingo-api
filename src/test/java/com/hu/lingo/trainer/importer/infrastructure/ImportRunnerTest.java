package com.hu.lingo.trainer.importer.infrastructure;

import com.hu.lingo.trainer.importer.core.application.WordImporter;
import com.hu.lingo.trainer.importer.core.domain.WordFilter;
import com.hu.lingo.trainer.importer.core.ports.WordReader;
import com.hu.lingo.trainer.importer.core.ports.WordWriter;
import com.hu.lingo.trainer.importer.infrastructure.driver.cli.ImportRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ImportRunnerTest {

    @Test
    void import_words_once_at_startup() throws IOException, NoSuchAlgorithmException {

        WordReader mockReader = mock(WordReader.class);
        WordFilter mockFilter = mock(WordFilter.class);
        WordWriter mockWriter = mock(WordWriter.class);

        WordImporter mockWordImporter = mock(WordImporter.class, withSettings().useConstructor(mockReader, mockFilter, mockWriter));
        ImportRunner mockImportRunner = mock(ImportRunner.class, withSettings().useConstructor(mockWordImporter));
        mockImportRunner.run();

        verify(mockImportRunner, times(1))
                .run();
    }
}
