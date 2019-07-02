/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.pictatture.services;

import com.bruno.pictatture.models.Endereco;
import com.bruno.pictatture.repository.EnderecoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService  {
  
    @Autowired
    private EnderecoRepository repository;
    
    public List<Endereco> findAll() {
        return repository.findAll();
    }
    
    public Endereco insert(Endereco endereco) {
        repository.save(endereco);
        return endereco;
    }
    
    public Endereco findById(Long id) {
        return repository.findById(id).get();
    }
    
    public Endereco update(Endereco endereco) {
        return repository.save(endereco);
    }
    
    public void remove(Long id) {
        repository.deleteById(id);
    }
    
    public Endereco findByCidade(String cidade) {
        return repository.findByCidade(cidade);
    }
    
}
