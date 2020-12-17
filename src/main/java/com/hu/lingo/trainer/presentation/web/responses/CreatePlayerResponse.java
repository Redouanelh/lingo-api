package com.hu.lingo.trainer.presentation.web.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class CreatePlayerResponse implements Serializable {
    private Integer id;
    private String message;
}
