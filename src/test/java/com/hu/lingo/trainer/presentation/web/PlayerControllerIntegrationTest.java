package com.hu.lingo.trainer.presentation.web;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hu.lingo.trainer.application.error.MissingParameterException;
import com.hu.lingo.trainer.application.error.PlayerAlreadyExistsException;
import com.hu.lingo.trainer.application.error.PlayerNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void throw_player_not_found_exception() throws Exception {
        this.mockMvc.perform(get("/player/{username}", "ABCQWERTYXX")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof PlayerNotFoundException));
    }

    @Test
    void throw_player_already_exists_exception() throws Exception {
        Object randomObj = new Object() {
            public final String username = "Redyy10";
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(post("/player")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)
        .characterEncoding("utf-8"))
                .andExpect(status().isConflict())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof PlayerAlreadyExistsException));
    }

    @Test
    void throw_missing_parameter_when_creating_player_exception() throws Exception {
        Object randomObj = new Object() {
            public final String poopname = "NewPlayer"; // needs to be "username" instead of "poopname"
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(post("/player")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof MissingParameterException));
    }
}
