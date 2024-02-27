package com.example.stationskitest;

import com.example.stationski.entities.Cours;
import com.example.stationski.entities.Support;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.services.CoursService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CoursServiceTest {

    private CoursService coursService;

    @Mock
    private CoursRepository coursRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        coursService = new CoursService(coursRepository);
    }

    @Test
    public void testListeCoursSnowBoard() {
        // Création de données de test
        Cours cours1 = new Cours();
        cours1.setNumCours(1);
        cours1.setPrix(50);
        cours1.setSupport(Support.SNOWBOARD);

        Cours cours2 = new Cours();
        cours2.setNumCours(2);
        cours2.setPrix(60);
        cours2.setSupport(Support.SKI);

        List<Cours> coursList = Arrays.asList(cours1, cours2);

        // Définition du comportement simulé du repository
        when(coursRepository.findBySupport(Support.SNOWBOARD)).thenReturn(coursList);

        // Appel de la méthode à tester
        coursService.listeCoursSnowBoard();

        // Vérification que la méthode de journalisation a été appelée avec les bons arguments
        verify(coursRepository, times(1)).findBySupport(Support.SNOWBOARD);
    }
}

