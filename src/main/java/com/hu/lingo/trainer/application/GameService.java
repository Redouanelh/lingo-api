package com.hu.lingo.trainer.application;

import com.hu.lingo.trainer.application.error.GameAlreadyExistsException;
import com.hu.lingo.trainer.application.error.GameNotSavedException;
import com.hu.lingo.trainer.application.error.PlayerNotFoundException;
import com.hu.lingo.trainer.data.GameRepository;
import com.hu.lingo.trainer.domain.entity.Game;
import com.hu.lingo.trainer.domain.entity.GameStatus;
import com.hu.lingo.trainer.domain.entity.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GameService extends BaseService<Game> {

    private GameRepository gameRepository;
    private PlayerService playerService;

    public GameService(GameRepository gameRepository, PlayerService playerService) {
        super(gameRepository);
        this.gameRepository = gameRepository;
        this.playerService = playerService;
    }

    @Transactional
    public Game createGame(String username) {
        Player player = this.playerService.findPlayerByUsername(username);

        Optional<Game> game = this.gameRepository.findByPlayerAndGameStatus(player, GameStatus.ACTIVE);
        if (game.isPresent()) throw new GameAlreadyExistsException(String.format("Player with username %s already has an active game running.", username));

        Game createdGame = this.gameRepository.save(new Game(player, GameStatus.ACTIVE));
        if (createdGame.getId() == null) throw new GameNotSavedException(String.format("Failed to save game for player: %s", username));

        return createdGame;
    }

}
