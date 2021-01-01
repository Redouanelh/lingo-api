package com.hu.lingo.trainer.data;

import com.hu.lingo.trainer.domain.entity.GameWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameWordRepository extends JpaRepository<GameWord, Integer> {
}
