package com.hu.lingo.trainer.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "game")
public class Game extends BaseEntity {

    @Column(name = "score")
    private int gameScore = 0;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @CreationTimestamp
    private Date created_at;

    @OneToOne()
    @JoinColumn(name = "player_fk")
    private Player player;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "round_fk")
    private Round round;

    public Game(Player player, GameStatus gameStatus, Round round) {
        this.player = player;
        this.gameStatus = gameStatus;
        this.round = round;
    }

    public TurnResponse performTurn(GameWord guess) {
        TurnResponse turnResponse = this.round.performTurn(guess);

//        if (turnResponse.getRoundStatus().equals(RoundStatus.CORRECT)) {
//            this.gameScore += 1;
//        }
        return turnResponse;
    }

    public void endGame() {
        this.gameStatus = GameStatus.FINISHED;
    }

}
