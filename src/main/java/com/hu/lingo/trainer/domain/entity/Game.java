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
    private int gameRound;

    @Column(name = "score")
    private int gameScore;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus = GameStatus.ACTIVE;

    @CreationTimestamp
    private Date created_at;

    @OneToOne()
    @JoinColumn(name = "player_fk")
    private Player player;

    public void correctWord() {
        this.gameScore += 1;
    }

    public void endGame() {
        this.gameStatus = GameStatus.FINISHED;
    }

}