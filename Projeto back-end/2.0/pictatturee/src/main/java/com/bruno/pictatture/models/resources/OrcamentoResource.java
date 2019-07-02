package com.bruno.pictatture.models.resources;

import com.bruno.pictatture.models.Dado;
import com.bruno.pictatture.models.Orcamento;
import com.bruno.pictatture.models.Tatuador;
import com.bruno.pictatture.models.User;
import com.bruno.pictatture.services.OrcamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@CrossOrigin
@RestController
@RequestMapping("/orcamentos")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class OrcamentoResource {

       
    @Autowired
    private OrcamentoService service;

    @GetMapping
    public List<Orcamento> findAll() {
        return service.findAll();
    }
    
       @PostMapping(value = "/insert")
    public Orcamento uploadFile(@RequestParam("tat") Tatuador tatuador,
            @RequestParam("cli") User user,
            @RequestParam("dado") Dado dado,
            @RequestPart("orcamento") String orcamento) throws IOException {
        Orcamento material = new ObjectMapper().readValue(orcamento, Orcamento.class);
         material.setUser(user);
         material.setTatuador(tatuador);
         material.setDado(dado);
        Orcamento d = service.insert(material);        
        return d;
        
    }
    
    @GetMapping("/{id}")
    public Orcamento findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Orcamento insert(@RequestBody @Valid Orcamento orcamento) {
        return service.insert(orcamento);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Orcamento> update(@PathVariable("id") Long id,
            @RequestBody @Valid Orcamento orcamento) {
        if (!id.equals(orcamento.getId()))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.update(orcamento));
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.remove(id);
    }
    
    @GetMapping("/tatuador")
    public List<Orcamento> findByTatuador(@RequestParam("tatuador") Tatuador tatuador) {
        return service.findByTatuador(tatuador);
    }
    
    @GetMapping("/cliente")
    public List<Orcamento> findByCliente(@RequestParam("cliente") User cliente) {
        return service.findByCliente(cliente);
    }
    
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<String> 
        handleValidationException(ConstraintViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
    
}