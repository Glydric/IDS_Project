package it.unicam.ids.studenti.ll.app.model.persistence.commerciante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommerciantePersistence {
    @Autowired
    private CommercianteRepository commercianteRepository;
}
