package com.hu.lingo.trainer.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class TurnResponse implements Serializable {
    RoundStatus roundStatus;
    ArrayList<Character> presentCharacters;
    String message;
}
