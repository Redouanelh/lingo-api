package com.hu.lingo.trainer.presentation.web.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindPlayerByUsernameResponse {
    private Integer id;
    private String message;
}
