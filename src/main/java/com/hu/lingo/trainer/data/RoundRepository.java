package com.hu.lingo.trainer.data;

import com.hu.lingo.trainer.domain.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<Round, Integer> {
}
