package com.hu.lingo.trainer.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "gameword")
public class GameWord extends BaseEntity {

    @Column(name = "word")
    private String word;

    @Column(name = "progress")
    private String progress;

    public GameWord(String word) {
        this.word = word;
        this.progress = word.substring(0, 1) + " _".repeat(word.length()-1) ;
    }

    public RoundStatus checkTurn(GameWord guess) {
        for (char c : guess.getWord().toCharArray()) {
            System.out.println(word.indexOf(c));
        }

        return null;
    }

}
