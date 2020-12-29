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

    @Column
    private String word;

    @Column
    private String progress;

    public GameWord(String word) {
        this.word = word;
        this.progress = word.substring(0, 1) + " _".repeat(word.length()-1) ;
    }

}
