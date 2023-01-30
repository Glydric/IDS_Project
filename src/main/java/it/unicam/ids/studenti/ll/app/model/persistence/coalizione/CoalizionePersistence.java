package it.unicam.ids.studenti.ll.app.model.persistence.coalizione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CoalizionePersistence {
    @Autowired
    private CoalizioneRepository coalizioneRepository;

    public List<CoalizioneEntity> getAllCoalizione(){
        List<CoalizioneEntity> coalizioneEntitys = new ArrayList<>();
        coalizioneRepository.findAll().forEach(coalizioneEntitys::add);
        return coalizioneEntitys;
    }

    public void addCoalizione(CoalizioneEntity coalizioneEntity){
        coalizioneRepository.save(coalizioneEntity);

    }
    public Optional<CoalizioneEntity> getCoalizione(String id){
        return coalizioneRepository.findById(UUID.fromString(id));

    }

    public void deleteCoalizione(String id){
        coalizioneRepository.deleteById(UUID.fromString(id));

    }
}


