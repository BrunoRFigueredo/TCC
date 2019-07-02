package com.bruno.pictatture.validators;

import com.bruno.pictatture.core.auth.IAuthentication;
import com.bruno.pictatture.core.validation.AbstractValidation;
import com.bruno.pictatture.models.Cliente;
import com.bruno.pictatture.models.Coments;
import com.bruno.pictatture.models.Dado;
import com.bruno.pictatture.models.Orcamento;
import com.bruno.pictatture.models.User;
import com.bruno.pictatture.models.resources.UserController;
import com.bruno.pictatture.repository.ClienteRepository;
import com.bruno.pictatture.repository.UserRepository;
import com.bruno.pictatture.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;


public class UserValidators {


    public static AbstractValidation checkMatchPassword() {
        return new AbstractValidation<User>("As senhas informadas não são iguais") {
            @Override
            public boolean validateEntity(User candidate) {
                return candidate.getPassword().equals(candidate.getMatchPassword());
            }
        };
    }

    public static AbstractValidation checkEmailDuplicado(UserRepository userRepository) {
        return new AbstractValidation<User>("Já existe um usuário cadastrado com este e-mail") {
            @Override
            public boolean validateEntity(User candidate) {
                if (candidate.getId() != null) {
                    return !userRepository.existsByEmailAndIdIsNot(candidate.getEmail(), candidate.getId());
                }
                return !userRepository.existsByEmail(candidate.getEmail());
            }
        };
    }
    
       public static AbstractValidation checkCPF(ClienteRepository userRepository) {
        return new AbstractValidation<User>("Este CPF já está em uso!") {
              
            @Override
            public boolean validateEntity(User candidate) {
                if (candidate.getId() != null) {
                    return !userRepository.existsByCpfAndIdIsNot(candidate.getCliente().getCpf(), candidate.getCliente().getId());
                }
                return !userRepository.existsByCpf(candidate.getCliente().getCpf());
            }
        };
    }

    public static AbstractValidation checkIsUsuario(Long user) {
        return new AbstractValidation<Dado>("Você não pode adicionar um orçamento a você mesmo!") {
            @Override
            public boolean validateEntity(Dado dado) {

                return !dado.getTatuador().getId().equals(user);
            
            }
        };
    }
    
     public static AbstractValidation checkIsTatuador(Long user) {
        return new AbstractValidation<Coments>("Você não pode adicionar um comentário a você mesmo!") {
            @Override
            public boolean validateEntity(Coments dado) {

                return !dado.getTatuador().getId().equals(user);
            
            }
        };
    }
    
    
    
    
    
   
    
    
     
}
