package com.hu.lingo.trainer.presentation.web;

import com.hu.lingo.trainer.application.PlayerService;
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
    public List<Player> allUsers() {
        return this.playerService.allPlayers();
    }

    @GetMapping("/{username}")
    public ResponseEntity<FindPlayerByUsernameResponse> user(@PathVariable String username) {

        return null;
    }

    @PostMapping
    public ResponseEntity<CreatePlayerResponse> savePlayer(@RequestBody CreatePlayerRequest request) {
        return null;
    }
}
