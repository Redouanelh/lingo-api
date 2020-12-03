package com.hu.lingo.trainer.presentation.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ControllerStartupTests {

    @Autowired
    private PlayerController userController;

    @Test
    @DisplayName("starts TestController")
    void startsUserController() {
        assertThat(userController).isNotNull();
    }

}
