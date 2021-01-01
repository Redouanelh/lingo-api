package com.hu.lingo.trainer.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "round")
public class Round extends BaseEntity {

    @Column(name = "tries")
    private int tries = 0;

    @Column(name = "roundNumber")
    private int roundNumber = 1;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "gameword_fk")
    private GameWord gameWord;

    public Round(GameWord gameWord) {
        this.gameWord = gameWord;
    }

    public TurnResponse performTurn(GameWord guess) {
        TurnResponse turnResponse = this.gameWord.checkTurn(guess);
        this.addTry();

        if (turnResponse.getRoundStatus().equals(RoundStatus.CORRECT)) this.roundNumber += 1;

        return turnResponse;
    }

    public void addTry() {
        this.tries += 1;
    }

    public void clearTries() {
        this.tries = 0;
    }
}
