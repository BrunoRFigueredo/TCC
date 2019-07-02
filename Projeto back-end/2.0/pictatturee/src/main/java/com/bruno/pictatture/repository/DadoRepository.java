/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.pictatture.repository;


import com.bruno.pictatture.models.Cliente;
import com.bruno.pictatture.models.Dado;
import com.bruno.pictatture.models.Tatuador;
import com.bruno.pictatture.models.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Pichau
 */
public interface DadoRepository extends JpaRepository<Dado, Long> {
    
    
    
    List<Dado> findByCliente(User cliente);
    
     List<Dado> findByTatuador(Tatuador tatuador);
    
}
