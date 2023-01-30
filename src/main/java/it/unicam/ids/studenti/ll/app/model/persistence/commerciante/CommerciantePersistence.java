package it.unicam.ids.studenti.ll.app.model.persistence.commerciante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommerciantePersistence {
    @Autowired
    private CommercianteRepository commercianteRepository;
    public List<CommercianteEntity> getAllCommerciante(){
        List<CommercianteEntity> commercianteEntitys = new ArrayList<>();
        commercianteRepository.findAll().forEach(commercianteEntitys::add);
        return commercianteEntitys;

    }

    public void addCommerciante(CommercianteEntity commercianteEntity){
        commercianteRepository.save(commercianteEntity);

    }
    public Optional<CommercianteEntity> getCommerciante(String ragioneSociale){
        return commercianteRepository.findById(ragioneSociale);
    }
    public void deleteCommerciante(String ragioneSociale){
        commercianteRepository.deleteById(ragioneSociale);

    }
}

