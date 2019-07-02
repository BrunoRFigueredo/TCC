/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.pictatture.services;


import com.bruno.pictatture.core.auth.IAuthentication;
import com.bruno.pictatture.models.Dado;
import com.bruno.pictatture.models.Tatuador;
import com.bruno.pictatture.models.User;
import com.bruno.pictatture.models.resources.UserController;
import com.bruno.pictatture.repository.DadoRepository;
import com.bruno.pictatture.validators.UserValidators;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class DadoService{

    
    @Autowired
    private DadoRepository repository;
    
    @Autowired
    private com.bruno.pictatture.core.validation.Validator validator;

     @Autowired
    private IAuthentication authentication;
     
   
     
     public Dado storeFile(Dado dado) {
         validate(dado);
         return repository.save(dado);
    }
    
    public List<Dado> findAll() {
        return repository.findAll();
    }
    
    public Dado insert(Dado dado) {
        
        return repository.save(dado);
    }
    
    public Dado findById(Long id) {
        return repository.findById(id).get();
    }
    
    public Dado update(Dado dado) {
        return repository.save(dado);
    }
    
    public void remove(Long id) {
        repository.deleteById(id);
    }
    
    public List<Dado> findByCliente(User cliente) {
        return repository.findByCliente(cliente);
    }
    
    public List<Dado> findByTatuador(Tatuador tatuador) {
        return repository.findByTatuador(tatuador);
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
    
    
    private void validate(Dado entity) {  
        validator.validate(entity,
                UserValidators.checkIsUsuario(getCurrent()));
        
    }
    
}