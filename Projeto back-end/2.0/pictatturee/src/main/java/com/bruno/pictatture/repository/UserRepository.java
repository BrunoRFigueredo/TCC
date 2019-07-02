package com.bruno.pictatture.repository;


import com.bruno.pictatture.models.Cliente;
import com.bruno.pictatture.models.Imagem;
import com.bruno.pictatture.models.Tatuador;
import com.bruno.pictatture.models.User;
import com.bruno.pictatture.validators.UploadFileResponse;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Component
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByEmailAndEnabledIsTrue(String email);

    List<User> findAllBy();
    
    User findUserById(Long id);
    
    @Query(value = "SELECT users.* FROM chl_owner.users INNER JOIN public.tatuador ON tatuador.id_tatuador = users.id_tatuador WHERE users.id_tatuador IS NOT NULL and tatuador.validacao = True",nativeQuery = true)
    List<User> getAllTatuador();

    boolean existsByEmailAndIdIsNot(String email, Long id);

    boolean existsByEmail(String email);


}
