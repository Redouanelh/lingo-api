package com.hu.lingo.trainer.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "game")
public class Game extends BaseEntity {

    @Transient
    private boolean timeUp = false;

    @Column(name = "lastMove")
    private Timestamp lastMove;

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

        this.lastMove = new Timestamp(System.currentTimeMillis());
    }

    public TurnResponse performTurn(GameWord guess) {
        long timer = this.getTimestampDifferenceInSeconds(new Timestamp(System.currentTimeMillis()), this.lastMove);
        if (timer > 10) this.timeUp = true;

        this.lastMove = new Timestamp(System.currentTimeMillis()); // Update the lastMove timestamp

        if (this.round.getTries() >= 5) {
            this.gameStatus = GameStatus.FINISHED;
            return new TurnResponse(RoundStatus.ROUND_LIMIT, null, "You have reached the maximum amount of tries: 5. Game has finished. You can find all your game scores using the Github documentation!");
        }

        if (this.timeUp) {
            this.timeUp = false; // Resetting timeUp attribute

            this.getRound().addTry();
            return new TurnResponse(RoundStatus.TIME_UP, null, "You failed to answer within 10 seconds! This try will not count for your progress, but will be added as an official try.");
        }

        TurnResponse turnResponse = this.round.performTurn(guess);

        if (turnResponse.getRoundStatus().equals(RoundStatus.ROUND_LIMIT)) return turnResponse;
        if (turnResponse.getRoundStatus().equals(RoundStatus.CORRECT)) {
            this.round.clearTries();
            this.gameScore += 1;
        }

        return turnResponse;
    }

    private long getTimestampDifferenceInSeconds(Timestamp currentTime, Timestamp oldTime) {
        long differenceInMilisec = currentTime.getTime() - oldTime.getTime();
        return differenceInMilisec / 1000;
    }

}
