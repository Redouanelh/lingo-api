package com.hu.lingo.trainer.presentation.web;

import com.hu.lingo.trainer.application.PlayerService;
import com.hu.lingo.trainer.application.error.MissingParameterException;
import com.hu.lingo.trainer.domain.entity.Player;
import com.hu.lingo.trainer.presentation.web.requests.CreatePlayerRequest;
import com.hu.lingo.trainer.presentation.web.responses.CreatePlayerResponse;
import com.hu.lingo.trainer.presentation.web.responses.FindPlayerByUsernameResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/all")
    public List<Player> allPlayers() {
        return this.playerService.allPlayers();
    }

    @GetMapping("/{username}")
    public ResponseEntity<FindPlayerByUsernameResponse> onePlayer(@PathVariable String username) {
        Player player = this.playerService.findPlayerByUsername(username);
        return ResponseEntity.ok(new FindPlayerByUsernameResponse(player, "Player was successfully found."));
    }

    @PostMapping
    public ResponseEntity<CreatePlayerResponse> createPlayer(@RequestBody CreatePlayerRequest request) {
        /* If person tries to register without 'username' parameter, application returns http BAD_REQUEST status with written message */
        if (request.getUsername() == null) throw new MissingParameterException("Username parameter required when creating a player.");

        Player player = this.playerService.savePlayer(request.getUsername());
        return ResponseEntity.ok(new CreatePlayerResponse(player, "Player was successfully created."));
    }
}
