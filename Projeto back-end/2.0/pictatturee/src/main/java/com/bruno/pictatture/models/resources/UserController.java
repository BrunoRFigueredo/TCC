package com.bruno.pictatture.models.resources;


import com.bruno.pictatture.core.auth.IAuthentication;
import com.bruno.pictatture.models.User;
import com.bruno.pictatture.repository.UserRepository;
import com.bruno.pictatture.services.UserService;
import com.bruno.pictatture.validators.UploadFileResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@CrossOrigin
@RestController
@RequestMapping("/users")
@Transactional
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    
    @Autowired
    private IAuthentication authentication;
     
     
    @GetMapping("/current")
    public User getCurrent() {
        User user = (User)  authentication.getAuthentication().getPrincipal();        
        return  user;
    }
    
    @GetMapping
    public List<User> getAll() {
       return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id) {
        return userRepository.findUserById(id);
    }

    @PostMapping
    public User insert(@RequestBody User dto) {
        dto.setTatuador(null);
        return userService.save(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User dto) {
     if (!id.equals(dto.getId()))
            return ResponseEntity.badRequest().build();
      if (dto.getPassword() == null) {
            return ResponseEntity.ok(userService.save(dto));
        }
        return ResponseEntity.ok(userService.saveWithoutPassword(dto));
    }

    @GetMapping("/tatuadores")
    public List<User> getAllTatuador(){
    return userService.getAllTatuador();
    }    
    
   @PostMapping(value = "/uploadFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("id") User user) throws IOException {
        
        User d = userService.storeFile(file,user);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .toUriString();

        return new UploadFileResponse("", fileDownloadUri,
                file.getContentType(), file.getSize());
    }

 
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        User userById = userRepository.findUserById(id);
        userRepository.delete(userById);
    }
    
    

}
