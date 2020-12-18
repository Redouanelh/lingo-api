package com.hu.lingo.trainer.application;

import com.hu.lingo.trainer.data.GameRepository;
import com.hu.lingo.trainer.domain.entity.Game;
import org.springframework.stereotype.Service;

@Service
public class GameService extends BaseService<Game> {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        super(gameRepository);
        this.gameRepository = gameRepository;
    }

}
