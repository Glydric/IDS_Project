package it.unicam.ids.studenti.ll.app.model.persistence.coalizione;

import it.unicam.ids.studenti.ll.app.model.Coalizione;
import it.unicam.ids.studenti.ll.app.model.ModelMapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CoalizionePersistence {
    @Autowired
    private CoalizioneRepository coalizioneRepository;

    public List<CoalizioneEntity> getAllCoalizione(){
        List<CoalizioneEntity> coalizioneEntitys = new ArrayList<>();
        coalizioneRepository.findAll().forEach(coalizioneEntitys::add);
        return coalizioneEntitys;
    }

    public void addCoalizione(Coalizione coalizione){
        CoalizioneEntity entity =
                ModelMapperConfig.mapper().map(
                        coalizione,
                        CoalizioneEntity.class
                );
        coalizioneRepository.save(entity);

    }
    public Coalizione getCoalizione(String id){
        Optional<CoalizioneEntity> opt = coalizioneRepository.findById(UUID.fromString(id));
        if (opt.isEmpty())
            throw new NoSuchElementException("Il commerciante non esiste");
        return ModelMapperConfig
                .mapper()
                .map(
                        opt.get(),
                        Coalizione.class
                );
//        return coalizioneRepository.findById(UUID.fromString(id));

    }

    public void deleteCoalizione(String id){
        coalizioneRepository.deleteById(UUID.fromString(id));

    }
}


