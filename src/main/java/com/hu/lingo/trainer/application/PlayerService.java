package com.hu.lingo.trainer.application;

import com.hu.lingo.trainer.data.PlayerRepository;
import com.hu.lingo.trainer.domain.entity.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerService extends BaseService<Player> {

    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        super(playerRepository);
        this.playerRepository = playerRepository;
    }

    @Transactional
    public List<Player> allUsers() {
        return this.playerRepository.findAll();
    }
}
