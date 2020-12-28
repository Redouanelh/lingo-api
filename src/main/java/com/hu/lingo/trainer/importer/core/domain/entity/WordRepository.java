package com.hu.lingo.trainer.importer.core.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {

    @Modifying
    @Query (value = "TRUNCATE table word",
            nativeQuery = true)
    void truncateTable();

}
