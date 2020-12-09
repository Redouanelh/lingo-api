package com.hu.lingo.trainer.importer.core.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChecksumRepsitory extends JpaRepository<Checksum, Integer> {

    @Query(value = "FROM Checksum WHERE hash = :hash")
    Checksum getChecksumByHash(@Param("hash") String hash);

}
