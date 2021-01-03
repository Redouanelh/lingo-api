package com.hu.lingo.trainer.presentation.web.responses;

import com.hu.lingo.trainer.domain.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
public class HighscoreResponse implements Serializable {
    private int gameScore;
    private int gameRound;
    private Player player;
    private Date gameDate;
    private String failedWord;
}
