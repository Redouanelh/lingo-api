package com.hu.lingo.trainer.presentation.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hu.lingo.trainer.application.error.*;
import com.hu.lingo.trainer.importer.error.CapitalException;
import com.hu.lingo.trainer.importer.error.NonExistentException;
import com.hu.lingo.trainer.importer.error.WrongLengthException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void throw_no_games_exception() throws Exception {
        this.mockMvc.perform(get("/game/all/{username}", "NoGamesPlayer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof NoGamesException));
    }

    @Test
    void throw_no_finished_games_exception() throws Exception {
        this.mockMvc.perform(get("/game/finished/{username}", "NoGamesPlayer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof NoFinishedGamesException));
    }

    @Test
    void throw_no_active_game_exception() throws Exception {
        Object randomObj = new Object() {
            public final String username = "Redyy10";
            public final String guess = "wiens";
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(put("/game/turn")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof NoActiveGameException));
    }

    @Test
    void throw_missing_parameter_exception_when_guessing() throws Exception {
        Object randomObj = new Object() {
            public final String username = "Redyy10";
            public final String gok = "wiens"; // must be "guess" instead of "gok"
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(put("/game/turn")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof MissingParameterException));
    }

    @Test
    void throw_missing_parameter_exception_when_creating_game() throws Exception {
        Object randomObj = new Object() {
            public final String poopname = "Redyy10"; // must be "username" instead of "poopname"
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(post("/game/start")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof MissingParameterException));
    }

    @Test
    void throw_game_already_exists_exception() throws Exception {
        Object randomObj = new Object() {
            public final String username = "ActiveGamePlayer";
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(post("/game/start")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isConflict())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof GameAlreadyExistsException));
    }

    @Test
    void throws_capital_exception_when_guessing_word() throws Exception {
        Object randomObj = new Object() {
            public final String username = "ActiveGamePlayer";
            public final String guess = "wiEns";
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(put("/game/turn")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isConflict())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof CapitalException));
    }

    @Test
    void throws_non_existent_word_exception_when_guessing_word() throws Exception {
        Object randomObj = new Object() {
            public final String username = "ActiveGamePlayer";
            public final String guess = "jkpql"; // non-existent 5 letter word
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(put("/game/turn")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isConflict())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof NonExistentException));
    }

    @Test
    void throws_wrong_length_exception_when_guessing_word() throws Exception {
        Object randomObj = new Object() {
            public final String username = "ActiveGamePlayer";
            public final String guess = "ervanaf"; // 5 letter word is the first round, using 7 letter word.
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(put("/game/turn")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isConflict())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof WrongLengthException));
    }

}
