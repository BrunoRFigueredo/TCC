package com.bruno.pictatture.models.resources;

import com.bruno.pictatture.models.Cliente;
import com.bruno.pictatture.models.Dado;
import com.bruno.pictatture.models.Endereco;
import com.bruno.pictatture.services.EnderecoService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

@CrossOrigin
@RestController
@RequestMapping("/enderecos")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class EnderecoResource {

  @Autowired
    private EnderecoService service;

   
   
     @GetMapping
    public List<Endereco> findAll() {
        return service.findAll();
    }
    
    
    @GetMapping("/{id}")
    public Endereco findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }
    
     
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco insert(@RequestBody @Valid Endereco endereco) {
        return service.insert(endereco);
    }
    
    
   
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> update(@PathVariable("id") Long id,
            @RequestBody @Valid Endereco endereco) {
        if (!id.equals(endereco.getId()))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.update(endereco));
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.remove(id);
    }
    
    @GetMapping("/cidade")
    public Endereco findByCidade(@RequestParam("cidade") String cidade) {
        return service.findByCidade(cidade);
    }
    
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<String> 
        handleValidationException(ConstraintViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
    
}
