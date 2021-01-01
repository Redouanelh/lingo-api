package com.hu.lingo.trainer.presentation.web.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTurnRequest implements Serializable {
    private String username;
    private String guess;
}
