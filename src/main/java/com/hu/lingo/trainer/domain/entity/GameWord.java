package com.hu.lingo.trainer.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

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
        this.progress = word.substring(0, 1) + "_".repeat(word.length()-1) ;
    }

    public TurnResponse checkTurn(GameWord guess) {
        StringBuilder checker = new StringBuilder(this.progress);
        ArrayList<Character> presentCharacters = new ArrayList<>();

        for (char c : guess.getWord().toCharArray()) {
            for (char d : this.word.toCharArray()) {
                if (this.word.indexOf(c) != -1 && guess.getWord().indexOf(c) == this.word.indexOf(c)) checker.setCharAt(this.word.indexOf(c), c);
                if (this.word.indexOf(c) != -1 && (guess.getWord().indexOf(c) != this.word.indexOf(c))) {
                    if (presentCharacters.contains(c)) break;
                    presentCharacters.add(c);
                }
            }
        }

        if (checker.toString().equals(this.word)) {
            this.progress = checker.toString();
            return new TurnResponse(RoundStatus.CORRECT, presentCharacters);
        }

        if (!(checker.toString().equals(this.progress)) && presentCharacters.isEmpty()) {
            this.progress = checker.toString();
            return new TurnResponse(RoundStatus.PRESENT_AT_CORRECT_INDEX, presentCharacters);
        }

        if (presentCharacters.isEmpty()) {
            this.progress = checker.toString();
            return new TurnResponse(RoundStatus.ABSENT, presentCharacters);
        } else {
            this.progress = checker.toString();
            return new TurnResponse(RoundStatus.PRESENT, presentCharacters);
        }
    }

}
