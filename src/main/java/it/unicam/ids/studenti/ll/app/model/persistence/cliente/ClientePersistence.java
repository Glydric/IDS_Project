package it.unicam.ids.studenti.ll.app.model.persistence.cliente;

import it.unicam.ids.studenti.ll.app.model.Cliente;
import it.unicam.ids.studenti.ll.app.model.ModelMapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientePersistence {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteEntity> getAllCliente(){
        List<ClienteEntity> ClienteEntitys = new ArrayList<>();
        clienteRepository.findAll().forEach(ClienteEntitys::add);
        return ClienteEntitys;

    }

    public void addCliente(Cliente cliente){
        ClienteEntity clienteEntity = ModelMapperConfig.mapper().map(cliente,ClienteEntity.class);
        clienteRepository.save(clienteEntity);

    }
    public Cliente getCliente(String id) {
        Optional<ClienteEntity> opt = clienteRepository.findById(UUID.fromString(id));
        if (opt.isEmpty())
            throw new NoSuchElementException("Il cliente non esiste");
        return ModelMapperConfig.mapper().map(opt.get(), Cliente.class);
    }

    public void deleteCliente(String id){
        clienteRepository.deleteById(UUID.fromString(id));

    }
}