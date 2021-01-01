package com.hu.lingo.trainer.application;

import com.hu.lingo.trainer.data.RoundRepository;
import com.hu.lingo.trainer.domain.entity.Round;

public class RoundService extends BaseService<Round> {

    private RoundRepository roundRepository;

    public RoundService(RoundRepository roundRepository) {
        super(roundRepository);
        this.roundRepository = roundRepository;
    }
}
