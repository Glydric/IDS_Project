package it.unicam.ids.studenti.ll.app.model.persistence.commerciante;

import it.unicam.ids.studenti.ll.app.model.Commerciante;
import it.unicam.ids.studenti.ll.app.model.ModelMapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CommerciantePersistence {
    @Autowired
    private CommercianteRepository commercianteRepository;

    public List<CommercianteEntity> getAllCommerciante() {
        List<CommercianteEntity> commercianteEntitys = new ArrayList<>();
        commercianteRepository.findAll().forEach(commercianteEntitys::add);
        return commercianteEntitys;

    }

    public void updateCommerciante(Commerciante commerciante) {
        CommercianteEntity entity =
                ModelMapperConfig.mapper().map(
                        commerciante,
                        CommercianteEntity.class
                );
        commercianteRepository.update(
                entity.getRagioneSociale(),
                entity.getLinkEsterno(),
                entity.getWantCoalize(),
                entity.getCoalizione()
        );
    }

    public void addCommerciante(Commerciante commerciante) throws IllegalArgumentException {
        if (exists(commerciante.getRagioneSociale())) {
            updateCommerciante(commerciante);
            throw new IllegalArgumentException("Commerciante già esistente");
        }

        commercianteRepository.save(
                ModelMapperConfig.mapper().map(
                        commerciante,
                        CommercianteEntity.class
                )
        );
    }

    public Commerciante getCommerciante(String ragioneSociale) {
        Optional<CommercianteEntity> opt = commercianteRepository.findById(ragioneSociale);
        if (opt.isEmpty())
            throw new NoSuchElementException("Il commerciante non esiste");
        return ModelMapperConfig
                .mapper()
                .map(
                        opt.get(),
                        Commerciante.class
                );
    }

    public void deleteCommerciante(String ragioneSociale) {
        commercianteRepository.deleteById(ragioneSociale);
    }

    public boolean exists(String ragioneSociale) {
        return commercianteRepository.findById(ragioneSociale).isPresent();
    }
}

