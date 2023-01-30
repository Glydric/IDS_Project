package it.unicam.ids.studenti.ll.app.model.persistence.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientePersistence {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteEntity> getAllCliente(){
        List<ClienteEntity> ClienteEntitys = new ArrayList<>();
        clienteRepository.findAll().forEach(ClienteEntitys::add);
        return ClienteEntitys;

    }

    public void addCliente(ClienteEntity clienteEntity){
        clienteRepository.save(clienteEntity);

    }
    public Optional<ClienteEntity> getCliente(String id){
        return clienteRepository.findById(UUID.fromString(id));

    }

    public void deleteCliente(String id){
        clienteRepository.deleteById(UUID.fromString(id));

    }
}