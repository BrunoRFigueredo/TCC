package com.bruno.pictatture.services;

import com.bruno.pictatture.models.Cliente;
import com.bruno.pictatture.models.Imagem;
import com.bruno.pictatture.models.Role;
import com.bruno.pictatture.models.Tatuador;
import com.bruno.pictatture.models.User;
import com.bruno.pictatture.repository.ClienteRepository;
import com.bruno.pictatture.repository.ImagemRepository;
import com.bruno.pictatture.repository.RoleRepository;
import com.bruno.pictatture.repository.UserRepository;
import com.bruno.pictatture.validators.UploadFileResponse;
import com.bruno.pictatture.validators.UserValidators;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import javax.validation.Validator;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private com.bruno.pictatture.core.validation.Validator validator;

    /**
     * Salva um usuário. Antes de salvar o usuário, faz o encode da senha e
     * executa as validações.
     *
     * @param entity Usuário a ser salvo.
     * @param role Nome da role do usuário.
     * @return usuário salvo
     */
    public User save(User entity) {
       if (entity.getTatuador() == null) {
            Role role = new Role();
            role.setName("ROLE_USERS");
            role.setId(new Long(2));
            entity.setRole(role);
        } else {
            Role role = new Role();
            role.setName("ROLE_ADMIN");
            role.setId(new Long(1));
            entity.setRole(role);
        }
        validate(entity);
        entity.setPassword(encryptPassword(entity.getPassword()));

        return userRepository.save(entity);
    }

    /**
     * Permite salvar o usuário sem precisar redefinir sua senha. Antes de
     * salvar o usuário, define matchPassword e executa as validações.
     *
     * @param entity Usuário a ser salvo
     * @param role Nome da Role para o usuário
     * @return Usuário salvo
     */
    public User saveWithoutPassword(User entity) {
        if (entity.getTatuador() == null) {
            Role role = new Role();
            role.setName("ROLE_USERS");
            role.setId(new Long(2));
            entity.setRole(role);
        } else {
            Role role = new Role();
            role.setName("ROLE_ADMIN");
            role.setId(new Long(1));
            entity.setRole(role);
        }
        entity.setMatchPassword(entity.getPassword());
        validate(entity);
        return userRepository.save(entity);
    }

    /**
     * Busca um objeto Role com base em um nome
     *
     * @param role Nome da Role a ser buscado
     * @return role encontrada
     */
    private Role getRole(String role) {
        Objects.requireNonNull(role, "É necessário informar a role do usuário");
        return roleRepository.findRoleByNameIs(role);
    }

    /**
     * Criptografa senha
     *
     * @param pass Senha a ser criptografada
     * @return senha criptografada
     */
    private String encryptPassword(String pass) {
        return passwordEncoder.encode(pass);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    public List<User> getAllTatuador(){
        return userRepository.getAllTatuador();
    }
    
    public User storeFile(MultipartFile file,User user) throws IOException{
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

      
           
            Imagem dbFile = new Imagem(file.getBytes(),fileName, file.getContentType());
            user.setImagem(dbFile);
            return userRepository.save(user);
      
    }
    
   
    public User findById(Long id) {
        return userRepository.findUserById(id);
    }
    
    /**
     * Executa as validações para a entidade User Serão executadas as
     * AbstractValidations e as anotações
     *
     * @param entity User a ser validado
     */
    private void validate(User entity) {
        validator.validate(entity,
                UserValidators.checkMatchPassword(),
                UserValidators.checkEmailDuplicado(userRepository),
                UserValidators.checkCPF(clienteRepository));
    }
}
