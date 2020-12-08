package com.hu.lingo.trainer.data;

import com.hu.lingo.trainer.domain.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {
}
