package com.hu.lingo.trainer.presentation.web.responses;

import com.hu.lingo.trainer.domain.entity.GameStatus;
import com.hu.lingo.trainer.domain.entity.TurnResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class PerformingTurnResponse implements Serializable {
    private int gameRound;
    private int tries;
    private int gameScore;
    private GameStatus gameStatus;
    private String wordProgress;
    private TurnResponse turn;
}
