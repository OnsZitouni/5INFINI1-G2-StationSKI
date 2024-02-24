package com.example.stationskitestunitaires;

import com.example.stationski.entities.Abonnement;
import com.example.stationski.entities.TypeAbonnement;
import com.example.stationski.repositories.AbonnementRepository;
import com.example.stationski.services.AbonnementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AbonnementServiceImpTest {
    @Mock
    private AbonnementRepository abonnementRepository;

    @InjectMocks
    private AbonnementService abonnementService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAbonnementByType() {
        // Création de données de test
        Abonnement abonnement1 = new Abonnement();
        abonnement1.setTypeAbon(TypeAbonnement.ANNUEL);

        Abonnement abonnement2 = new Abonnement();
        abonnement2.setTypeAbon(TypeAbonnement.MENSUEL);

        when(abonnementRepository.findByTypeAbon(TypeAbonnement.ANNUEL)).thenReturn(new HashSet<>(Arrays.asList(abonnement1)));

        // Appel de la méthode à tester
        Set<Abonnement> result = abonnementService.getAbonnementByType(TypeAbonnement.ANNUEL);

        // Vérification du résultat
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(abonnement1));
        assertFalse(result.contains(abonnement2));
    }

    @Test
    public void testRetrieveAbonnementByDates() {
        // Création de données de test
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        Abonnement abonnement1 = new Abonnement();
        Abonnement abonnement2 = new Abonnement();

        List<Abonnement> abonnements = Arrays.asList(abonnement1, abonnement2);

        when(abonnementRepository.getAbonnementsByDateDebutBetween(startDate, endDate)).thenReturn(abonnements);

        // Appel de la méthode à tester
        List<Abonnement> result = abonnementService.retrieveAbonnementByDates(startDate, endDate);

        // Vérification du résultat
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(abonnement1));
        assertTrue(result.contains(abonnement2));
    }
}
