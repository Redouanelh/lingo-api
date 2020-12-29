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

    @Column(name = "round")
    private int gameRound = 1;

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
    @JoinColumn(name = "gameword_fk")
    private GameWord gameWord;

    public Game(Player player, GameStatus gameStatus, GameWord gameWord) {
        this.player = player;
        this.gameStatus = gameStatus;
        this.gameWord = gameWord;
    }

    public void correctWord() {
        this.gameScore += 1;
    }

    public void endGame() {
        this.gameStatus = GameStatus.FINISHED;
    }

}
