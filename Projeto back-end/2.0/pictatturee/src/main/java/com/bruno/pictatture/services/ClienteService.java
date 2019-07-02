 package com.bruno.pictatture.services;


import com.bruno.pictatture.models.Cliente;
import com.bruno.pictatture.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin
public class ClienteService {
    
    @Autowired
    private ClienteRepository repository;
    
  
    public List<Cliente> findAll() {
        return repository.findAll();
    }
    
    public Cliente insert(Cliente cliente) {
        return repository.save(cliente);
    }
    
    public Cliente findById(Long id) {
        return repository.findById(id).get();
    }
    
    public Cliente update(Cliente cliente) {
        return repository.save(cliente);
    }
    
    public void remove(Long id) {
        repository.deleteById(id);
    }
    
    public Cliente findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
    
  
}
