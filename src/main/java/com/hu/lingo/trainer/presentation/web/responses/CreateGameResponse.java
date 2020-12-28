package com.hu.lingo.trainer.presentation.web.responses;

import com.hu.lingo.trainer.domain.entity.GameStatus;
import com.hu.lingo.trainer.domain.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class CreateGameResponse implements Serializable {
    private int gameId;
    private int gameRound;
    private int gameScore;
    private GameStatus gameStatus;
    private Player player;
    private String message;
}
