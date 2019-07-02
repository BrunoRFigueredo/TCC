/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.pictatture.services;


import com.bruno.pictatture.models.Coments;
import com.bruno.pictatture.models.Orcamento;
import com.bruno.pictatture.models.Tatuador;
import com.bruno.pictatture.models.User;
import com.bruno.pictatture.repository.ComentsRepository;

import com.bruno.pictatture.repository.OrcamentoRepository;
import com.bruno.pictatture.validators.UserValidators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ComentsService{

    @Autowired
    private ComentsRepository repository;
    
        @Autowired
    private com.bruno.pictatture.core.validation.Validator validator;

    
    public List<Coments> findAll() {
        return repository.findAll();
    }
    
    public Coments insert(Coments coments) {
        validate(coments);
       return repository.save(coments);
    }
    

    public Coments findById(Long id) {
        return repository.findById(id).get();
    }
    
    public Coments update(Coments coments) {
        return repository.save(coments);
    }
    
      public Long getCurrent() {
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         User user = (User) auth.getPrincipal();
         if(user.getTatuador() == null){
         return new Long(0);
         }else{
         return user.getTatuador().getId();
         }
     }
    
    public void remove(Long id) {
        repository.deleteById(id);
    }
    
    public List<Coments> findByTatuador(Tatuador tatuador) {
        return repository.findByTatuador(tatuador);
    }
    
        public List<Coments> findByCliente(User cliente) {
        return repository.findByCliente(cliente);
    }

         
    private void validate(Coments entity) {  
        validator.validate(entity,
                UserValidators.checkIsTatuador(getCurrent()));
        
    }
}
