package com.hu.lingo.trainer.data;

import com.hu.lingo.trainer.domain.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Optional<Player> findPlayerByUsername(String username);
}
