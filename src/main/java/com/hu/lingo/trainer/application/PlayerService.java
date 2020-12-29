package com.hu.lingo.trainer.application;

import com.hu.lingo.trainer.application.error.PlayerAlreadyExistsException;
import com.hu.lingo.trainer.application.error.PlayerNotFoundException;
import com.hu.lingo.trainer.application.error.PlayerNotSavedException;
import com.hu.lingo.trainer.data.PlayerRepository;
import com.hu.lingo.trainer.domain.entity.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService extends BaseService<Player> {

    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        super(playerRepository);
        this.playerRepository = playerRepository;
    }

    @Transactional
    public List<Player> allPlayers() {
        return this.playerRepository.findAll();
    }

    @Transactional
    public Player findPlayerByUsername(String username) {
        Optional<Player> player = this.playerRepository.findPlayerByUsername(username);

        /* If player does not exist in database, application returns http NOT_FOUND status with written message */
        if (player.isEmpty()) throw new PlayerNotFoundException(String.format("Player with username %s not found, you should register.", username));

        return player.get();
    }

    @Transactional
    public Player savePlayer(String username) {
        Optional<Player> player = this.playerRepository.findPlayerByUsername(username);
        if (player.isEmpty()) throw new PlayerAlreadyExistsException(String.format("Player with username %s already exists! Use a unique username.", username));

        Player savedPlayer = this.playerRepository.save(new Player(username));

        /* If fails to save player in database, application returns http CONFLICT status with written message */
        if (savedPlayer.getId() == null) throw new PlayerNotSavedException(String.format("Failed to save player with username %s.", username));

        return savedPlayer;
    }
}
