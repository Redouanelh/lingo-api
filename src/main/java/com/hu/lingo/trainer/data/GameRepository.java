package com.hu.lingo.trainer.data;

import com.hu.lingo.trainer.domain.entity.Game;
import com.hu.lingo.trainer.domain.entity.GameStatus;
import com.hu.lingo.trainer.domain.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Optional<Game> findByPlayerAndGameStatus(Player player, GameStatus gameStatus);
    List<Game> findAllByGameStatusAndPlayer(GameStatus gameStatus, Player player);
    List<Game> findAllByPlayer(Player player);

    @Query(value = "SELECT * FROM game ORDER BY score DESC LIMIT 5", nativeQuery = true)
    List<Game> findHighscores();
}
