package com.hu.lingo.trainer.importer.core.domain.entity;

import com.hu.lingo.trainer.domain.entity.BaseEntity;
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
@AllArgsConstructor
@Entity
@Table(name = "word")
public class Word extends BaseEntity {

    @Column
    private String word;

    @Column
    private int length;
}
