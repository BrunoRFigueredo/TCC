package com.bruno.pictatture.models.resources;

import com.bruno.pictatture.models.Cliente;
import com.bruno.pictatture.models.Dado;
import com.bruno.pictatture.models.Imagem;
import com.bruno.pictatture.models.Tatuador;
import com.bruno.pictatture.models.User;
import com.bruno.pictatture.services.DadoService;
import com.bruno.pictatture.validators.UploadFileResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin
@RestController
@RequestMapping("/dados")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class DadoResource  {

   @Autowired
    private DadoService service;

    @GetMapping
    public List<Dado> findAll() {
        return service.findAll();
    }
    
    @GetMapping("/{id}")
    public Dado findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dado insert(@RequestBody @Valid Dado dado) {
        return service.insert(dado);
    }
    
      @PostMapping(value = "/uploadFile")
    public Dado uploadFile(@RequestParam("file") MultipartFile file, 
            @RequestParam("tat") Tatuador tatuador,
            @RequestParam("cli") User cliente,
            @RequestPart("dado") String dado) throws IOException {
        
        Dado material = new ObjectMapper().readValue(dado, Dado.class);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
         Imagem dbFile = new Imagem(file.getBytes(),fileName, file.getContentType());
         material.setCliente(cliente);
         material.setTatuador(tatuador);
         material.setImagem(dbFile);
        Dado d = service.storeFile(material);
        return d;
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Dado> update(@PathVariable("id") Long id,
            @RequestParam("dado") Dado dado) {
            dado.setId(id);
            dado.setEnabled(Boolean.TRUE);
        if (!id.equals(dado.getId()))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.update(dado));
    }
    
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.remove(id);
    }
    
    @GetMapping("/cliente")
        public List<Dado> findByCliente(@RequestParam("cliente") User cliente) {
        return service.findByCliente(cliente);
    }
        
        
        @GetMapping("/tatuador")
        public List<Dado> findByTatuador(@RequestParam("tatuador") Tatuador tatuador) {
        return service.findByTatuador(tatuador);
    }
    
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<String> 
        handleValidationException(ConstraintViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
    
}
