package com.example.stationski.services;

import com.example.stationski.entities.Piste;
import com.example.stationski.entities.Skieur;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.PisteRepository;
import com.example.stationski.repositories.SkieurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class SkieurServiceTest {
    @Mock
    private SkieurRepository skieurRepository;

    @Mock
    private PisteRepository pisteRepository;

    @Mock
    private CoursRepository coursRepository;

    @InjectMocks
    private SkieurService skieurService;

    @Test
    public void testAssignSkieurToPiste() {
        // Création d'un skieur et d'une piste
        Skieur skieur = new Skieur();
        skieur.setNumSkieur(123L);

        Piste piste = new Piste();
        piste.setNumPiste(456L);

        // Configuration du comportement du repository pour retrouver le skieur et la piste
        when(skieurRepository.findByNumSkieur(123L)).thenReturn(skieur);
        when(pisteRepository.findByNumPiste(456L)).thenReturn(piste);

        // Appel de la méthode à tester
        Skieur updatedSkieur = skieurService.assignSkieurToPiste(123L, 456L);

        // Vérification que la piste a bien été ajoutée au skieur
        verify(skieurRepository, times(1)).findByNumSkieur(123L);
        verify(pisteRepository, times(1)).findByNumPiste(456L);
        verify(skieurRepository, times(1)).save(skieur);
    }
}