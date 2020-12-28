package com.hu.lingo.trainer.presentation.web.responses;

import com.hu.lingo.trainer.domain.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindPlayerByUsernameResponse {
    private Player player;
    private String message;
}
