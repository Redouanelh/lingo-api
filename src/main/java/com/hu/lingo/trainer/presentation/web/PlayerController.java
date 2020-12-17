package com.hu.lingo.trainer.presentation.web;

import com.hu.lingo.trainer.application.PlayerService;
import com.hu.lingo.trainer.domain.entity.Player;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public List<Player> allUsers() {
        return this.playerService.allUsers();
    }

    @PostMapping("/dummy")
    public Boolean addDummy() {
        Player dummy = new Player("dummy");

        return this.playerService.save(dummy);
    }
}
