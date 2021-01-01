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
    private boolean timeUp;

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

        // if timelimt blabla true..doe tries +1 en dan return TIME_UP jwz en zet in message ook ja je bent nu op beurt ...
        // aan het einde van een beurt elke keer weer die timeboolean op false zetten!!! Doe dit in gameservice, dus nadat een
        // game.performturn is gedaan. Dan heb je gelijk alle wegen afgedekt.

        TurnResponse turnResponse = this.round.performTurn(guess);

//        if (turnResponse.getRoundStatus().equals(RoundStatus.CORRECT)) {
//            this.gameScore += 1;
//        }

        this.lastMove = new Timestamp(System.currentTimeMillis()); // Update the lastMove timestamp

        return turnResponse;
    }

    private long getTimestampDifferenceInSeconds(Timestamp currentTime, Timestamp oldTime) {
        long differenceInMilisec = currentTime.getTime() - oldTime.getTime();
        return differenceInMilisec / 1000;
    }

}
