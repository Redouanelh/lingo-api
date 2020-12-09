package com.hu.lingo.trainer.importer.core.application;

import com.hu.lingo.trainer.importer.core.domain.entity.Checksum;
import com.hu.lingo.trainer.importer.core.domain.entity.ChecksumRepsitory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class FileService extends BaseService<Checksum> {

    private ChecksumRepsitory checksumRepsitory;
    private final Path path;

    public FileService(@Value("${lingo.source}") Path path, ChecksumRepsitory checksumRepsitory) {
        super(checksumRepsitory);
        this.checksumRepsitory = checksumRepsitory;
        this.path = path;
    }

    public Checksum generateHash() throws NoSuchAlgorithmException, IOException {
        File file = new File(String.valueOf(this.path));
        Checksum checksum = new Checksum();

        MessageDigest md5Digest = MessageDigest.getInstance("MD5");
        checksum = checksum.getFileChecksum(md5Digest, file);

        return checksum;
    }

    public boolean findChecksum(Checksum checksum) {
        return this.checksumRepsitory.getChecksumByHash(checksum.getHash()) != null;
    }

}
