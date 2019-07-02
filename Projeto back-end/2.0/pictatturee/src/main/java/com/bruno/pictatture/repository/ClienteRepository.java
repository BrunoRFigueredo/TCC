/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.pictatture.repository;

import com.bruno.pictatture.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Pichau
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    Cliente findByCpf(String cpf);
    
    boolean existsByCpfAndIdIsNot(String cpf, Long id);
    
    boolean existsByCpf(String cpf);

    
}
