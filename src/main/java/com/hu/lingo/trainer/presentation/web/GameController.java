package com.hu.lingo.trainer.presentation.web;

import com.hu.lingo.trainer.application.GameService;
import com.hu.lingo.trainer.application.error.MissingParameterException;
import com.hu.lingo.trainer.domain.entity.Game;
import com.hu.lingo.trainer.presentation.web.requests.CreateGameRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity<?> startGame(@RequestBody CreateGameRequest request) {
        /* If person tries to create a game without the 'username' parameter, application returns http BAD_REQUEST status with written message */
        if (request.getUsername() == null) throw new MissingParameterException("Username parameter required when starting a game.");

        Game game = this.gameService.createGame(request.getUsername());

        return null;
    }

    // alle highscores ophalen

}
