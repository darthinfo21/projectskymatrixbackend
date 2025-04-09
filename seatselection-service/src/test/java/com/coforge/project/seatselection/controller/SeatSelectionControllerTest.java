package com.coforge.project.seatselection.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.coforge.project.seatselection.model.SeatSelection;
import com.coforge.project.seatselection.service.SeatSelectionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(SeatSelectionController.class)
public class SeatSelectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeatSelectionService seatSelectionService;

    @InjectMocks
    private SeatSelectionController seatSelectionController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private SeatSelection seatSelection;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        seatSelection = new SeatSelection();
        seatSelection.setId(1L);
        seatSelection.setFlightId(100L);
        seatSelection.setUserId(200L);
        seatSelection.setSeatNumber("1A");
    }

    @Test
    void testSelectSeat() throws Exception {
        when(seatSelectionService.selectSeat(seatSelection)).thenReturn(seatSelection);

        mockMvc.perform(post("/api/seats/select")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(seatSelection)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(seatSelection.getId()))
                .andExpect(jsonPath("$.flightId").value(seatSelection.getFlightId()))
                .andExpect(jsonPath("$.userId").value(seatSelection.getUserId()))
                .andExpect(jsonPath("$.seatNumber").value(seatSelection.getSeatNumber()));
    }

    @Test
    void testGetSeatsByFlightId() throws Exception {
        List<SeatSelection> seatSelections = Arrays.asList(seatSelection);
        when(seatSelectionService.getSeatsByFlightId(100L)).thenReturn(seatSelections);

        mockMvc.perform(get("/api/seats/flight/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(seatSelection.getId()))
                .andExpect(jsonPath("$[0].flightId").value(seatSelection.getFlightId()))
                .andExpect(jsonPath("$[0].userId").value(seatSelection.getUserId()))
                .andExpect(jsonPath("$[0].seatNumber").value(seatSelection.getSeatNumber()));
    }

    @Test
    void testGetSeatsByUserId() throws Exception {
        List<SeatSelection> seatSelections = Arrays.asList(seatSelection);
        when(seatSelectionService.getSeatsByUserId(200L)).thenReturn(seatSelections);

        mockMvc.perform(get("/api/seats/user/200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(seatSelection.getId()))
                .andExpect(jsonPath("$[0].flightId").value(seatSelection.getFlightId()))
                .andExpect(jsonPath("$[0].userId").value(seatSelection.getUserId()))
                .andExpect(jsonPath("$[0].seatNumber").value(seatSelection.getSeatNumber()));
    }
}

