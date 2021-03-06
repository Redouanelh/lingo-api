package com.hu.lingo.trainer.domain.entity;

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

        for (int i = 0; i < this.word.length(); i++) {
            if (this.word.toCharArray()[i] == guess.getWord().toCharArray()[i]) checker.setCharAt(i, this.word.toCharArray()[i]);
            if (this.word.toCharArray()[i] != guess.getWord().toCharArray()[i] && this.word.indexOf(guess.getWord().toCharArray()[i]) != -1) {
                if (presentCharacters.contains(guess.getWord().toCharArray()[i])) break; // only add present character once.
                presentCharacters.add(guess.getWord().toCharArray()[i]);
            }
        }

        if (checker.toString().equals(this.word)) {
            this.progress = checker.toString();
            return new TurnResponse(RoundStatus.CORRECT, presentCharacters, String.format("Your try is correct! The word was: %s. You gained 1 point! The next round has started.", this.word));
        }

        if (!(checker.toString().equals(this.progress)) && presentCharacters.isEmpty()) {
            this.progress = checker.toString();
            return new TurnResponse(RoundStatus.PRESENT_AT_CORRECT_INDEX, presentCharacters, "Your try has characters at the correct spot, goodjob!");
        }

        if (presentCharacters.isEmpty()) {
            this.progress = checker.toString();
            return new TurnResponse(RoundStatus.ABSENT, presentCharacters, "Your try has no present characters!");
        } else {
            this.progress = checker.toString();
            return new TurnResponse(RoundStatus.PRESENT, presentCharacters, "Your try has present characters, but at the wrong spot!");
        }
    }

}
