package com.hu.lingo.trainer.presentation.web.responses;

import com.hu.lingo.trainer.domain.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class CreatePlayerResponse implements Serializable {
    private Player player;
    private String message;
}
