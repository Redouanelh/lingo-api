package com.hu.lingo.trainer.application;

import com.hu.lingo.trainer.application.error.GameAlreadyExistsException;
import com.hu.lingo.trainer.application.error.GameNotSavedException;
import com.hu.lingo.trainer.application.error.NoActiveGameException;
import com.hu.lingo.trainer.data.GameRepository;
import com.hu.lingo.trainer.domain.entity.*;
import com.hu.lingo.trainer.importer.infrastructure.driver.controller.WordImportController;
import com.hu.lingo.trainer.presentation.web.responses.HighscoreResponse;
import com.hu.lingo.trainer.presentation.web.responses.PerformingTurnResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService extends BaseService<Game> {

    private GameRepository gameRepository;
    private PlayerService playerService;
    private WordImportController wordImportController;

    public GameService(GameRepository gameRepository, PlayerService playerService, WordImportController wordImportController) {
        super(gameRepository);
        this.gameRepository = gameRepository;
        this.playerService = playerService;
        this.wordImportController = wordImportController;
    }

    @Transactional
    public List<HighscoreResponse> highestScores() {
        List<Game> games = this.gameRepository.findHighscores();
        List<HighscoreResponse> highscores = new ArrayList<>();

        for (Game game : games) {
            highscores.add(new HighscoreResponse(game.getGameScore(), game.getRound().getRoundNumber(), game.getPlayer(), game.getCreated_at(), game.getRound().getGameWord().getWord()));
        }

        return highscores;
    }

    @Transactional
    public List<Game> allGames(String username) {
        Player player = this.playerService.findPlayerByUsername(username);
        return this.gameRepository.findAllByPlayer(player);
    }

    @Transactional
    public List<Game> allFinishedGames(String username) {
        Player player = this.playerService.findPlayerByUsername(username);
        return this.gameRepository.findAllByGameStatusAndPlayer(GameStatus.FINISHED, player);
    }

    @Transactional
    public Game createGame(String username) {
        Player player = this.playerService.findPlayerByUsername(username);
        GameWord gameWord = new GameWord(this.wordImportController.randomWord(5)); // Game starts with a 5 letter word.

        Optional<Game> game = this.gameRepository.findByPlayerAndGameStatus(player, GameStatus.ACTIVE);
        if (game.isPresent()) throw new GameAlreadyExistsException(String.format("Player with username %s already has an active game running.", username));

        Game createdGame = this.gameRepository.save(new Game(player, GameStatus.ACTIVE, new Round(gameWord)));
        if (createdGame.getId() == null) throw new GameNotSavedException(String.format("Failed to save game for player: %s", username));

        return createdGame;
    }

    @Transactional
    public Game findGame(String username) {
        Player player = this.playerService.findPlayerByUsername(username);

        Optional<Game> game = this.gameRepository.findByPlayerAndGameStatus(player, GameStatus.ACTIVE);
        if (game.isEmpty()) throw new NoActiveGameException(String.format("Player with username %s has no active game running.", username));

        return game.get();
    }

    @Transactional
    public PerformingTurnResponse performTurn(Game game, GameWord guess) {
        this.wordImportController.guessValidator(game.getRound().getGameWord().getWord(), guess.getWord());

        TurnResponse turnResponse = game.performTurn(guess);

        // New random word for the next round.
        if (turnResponse.getRoundStatus().equals(RoundStatus.CORRECT)) {
            int gameWordLength = game.getRound().getGameWord().getWord().length();
            GameWord newGameWord = null;

            if (gameWordLength == 5) newGameWord = new GameWord(this.wordImportController.randomWord(6)); // Previous was 5 letters, now 6.
            if (gameWordLength == 6) newGameWord = new GameWord(this.wordImportController.randomWord(7)); // Previous was 6 letters, now 7.
            if (gameWordLength == 7) newGameWord = new GameWord(this.wordImportController.randomWord(5)); // Previous was 7 letters, now 5.

            game.getRound().setGameWord(newGameWord);
        }

        return new PerformingTurnResponse(game.getRound().getRoundNumber(), game.getRound().getTries(), game.getGameScore(), game.getGameStatus(), game.getRound().getGameWord().getProgress(), turnResponse);
    }

}
