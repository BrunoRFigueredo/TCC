/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.pictatture.services;


import com.bruno.pictatture.models.Orcamento;
import com.bruno.pictatture.models.Tatuador;
import com.bruno.pictatture.models.User;

import com.bruno.pictatture.repository.OrcamentoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrcamentoService{

    @Autowired
    private OrcamentoRepository repository;
    
    public List<Orcamento> findAll() {
        return repository.findAll();
    }
    
    public Orcamento insert(Orcamento orcamento) {
       return repository.save(orcamento);
    }
    
     
    
    public Orcamento findById(Long id) {
        return repository.findById(id).get();
    }
    
    public Orcamento update(Orcamento orcamento) {
        return repository.save(orcamento);
    }
    
    public void remove(Long id) {
        repository.deleteById(id);
    }
    
    public List<Orcamento> findByTatuador(Tatuador tatuador) {
        return repository.findByTatuador(tatuador);
    }
    
        public List<Orcamento> findByCliente(User cliente) {
        return repository.findByCliente(cliente);
    }
  
}
