/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.pictatture.repository;

import com.bruno.pictatture.models.Orcamento;
import com.bruno.pictatture.models.Tatuador;
import com.bruno.pictatture.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Pichau
 */
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
    
    List<Orcamento> findByTatuador(Tatuador tatuador);
    
     List<Orcamento> findByCliente(User cliente);
    
}
