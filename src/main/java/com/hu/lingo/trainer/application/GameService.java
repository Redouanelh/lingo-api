package com.hu.lingo.trainer.application;

import com.hu.lingo.trainer.application.error.PlayerNotFoundException;
import com.hu.lingo.trainer.data.GameRepository;
import com.hu.lingo.trainer.domain.entity.Game;
import com.hu.lingo.trainer.domain.entity.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        return null;
    }

}
