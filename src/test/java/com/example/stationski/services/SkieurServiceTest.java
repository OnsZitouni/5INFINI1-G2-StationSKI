package com.example.stationski.services;

import com.example.stationski.entities.Cours;
import com.example.stationski.entities.Piste;
import com.example.stationski.entities.Skieur;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.PisteRepository;
import com.example.stationski.repositories.SkieurRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class SkieurServiceTest {
    @InjectMocks
SkieurService skieurService;

    @Mock
    SkieurRepository skieurRepository;

    @Mock
    PisteRepository pisteRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAssignSkieurToPiste() {
        // Prepare test data
        Skieur skieur = new Skieur();
        skieur.setNumSkieur(123L);
        Piste piste = new Piste();
        piste.setNumPiste(456L);

        // Mock repository methods
        when(skieurRepository.findByNumSkieur(123L)).thenReturn(skieur);
        when(pisteRepository.findByNumPiste(456L)).thenReturn(piste);

        // Test method
        Skieur result = skieurService.assignSkieurToPiste(123L, 456L);

        // Verify the result
        assertNotNull(result);
        assertTrue(result.getPistes().contains(piste));
    }
}