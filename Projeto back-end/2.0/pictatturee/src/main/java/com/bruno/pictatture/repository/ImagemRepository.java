/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.pictatture.repository;

import com.bruno.pictatture.models.Imagem;
import com.bruno.pictatture.models.Tatuador;
import com.bruno.pictatture.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    
    
}
