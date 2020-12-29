package com.hu.lingo.trainer.presentation.web.controller;

import com.hu.lingo.trainer.application.GameService;
import com.hu.lingo.trainer.application.error.MissingParameterException;
import com.hu.lingo.trainer.domain.entity.Game;
import com.hu.lingo.trainer.presentation.web.requests.CreateGameRequest;
import com.hu.lingo.trainer.presentation.web.requests.CreateTurnRequest;
import com.hu.lingo.trainer.presentation.web.responses.CreateGameResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity<CreateGameResponse> startGame(@RequestBody CreateGameRequest request) {
        /* If person tries to create a game without the 'username' parameter, application returns http BAD_REQUEST status with written message */
        if (request.getUsername() == null) throw new MissingParameterException("Username parameter required when starting a game.");

        Game game = this.gameService.createGame(request.getUsername());
        return ResponseEntity.ok(new CreateGameResponse(game.getId(), game.getGameRound(), game.getGameScore(), game.getGameStatus(), game.getPlayer(), String.format("Game was successfully created. The word has %s letters. You have 10 seconds for a guess, goodluck!", game.getGameWord().getWord().length()), game.getGameWord().getProgress()));
    }

    @PutMapping("/turn")
    public ResponseEntity<?> takeTurn(@RequestBody CreateTurnRequest request) {
        if (request.getUsername() == null) throw new MissingParameterException("Username parameter required when performing a turn.");
        if (request.getGuess() == null) throw new MissingParameterException("Guess parameter required when performing a turn.");

        Game game = this.gameService.findGame(request.getUsername());
        
        return null;
    }

    // alle highscores ophalen

    // alle games ophalen of alleen alle + active games of alle finished?

}
