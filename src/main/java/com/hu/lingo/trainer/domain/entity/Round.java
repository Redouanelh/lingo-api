package com.hu.lingo.trainer.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
//        if (this.roundNumber >= 5) return RoundStatus.ROUND_LIMIT;

        // TIME LIMIT... Return hier die status

//        this.tries += 1;
//        this.roundNumber += 1;
        System.out.println(this.gameWord.checkTurn(guess).getRoundStatus());
        for(char c : this.gameWord.checkTurn(guess).getPresentCharacters()) {
            System.out.println(c);
        }
        return this.gameWord.checkTurn(guess);
    }
}
