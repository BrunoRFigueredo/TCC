package com.bruno.pictatture.models.resources;

import com.bruno.pictatture.models.Coments;
import com.bruno.pictatture.models.Tatuador;
import com.bruno.pictatture.models.User;
import com.bruno.pictatture.services.ComentsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
@RequestMapping("/coments")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ComentsResource {

       
    @Autowired
    private ComentsService service;

    @GetMapping
    public List<Coments> findAll() {
        return service.findAll();
    }
    
       @PostMapping(value = "/insert")
    public Coments uploadFile(@RequestParam("tat") Tatuador tatuador,
            @RequestParam("cli") User user,
            @RequestPart("coments") String coments) throws IOException {
        Coments material = new ObjectMapper().readValue(coments, Coments.class);
        Date d = new Date();
        String dStr = DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
        material.setDataComent(dStr);
         material.setCliente(user);
         material.setTatuador(tatuador);
        Coments c = service.insert(material);        
        return c;
        
    }
    
    @GetMapping("/{id}")
    public Coments findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Coments insert(@RequestBody Coments coments) {
        Date date = new Date();
	Calendar cal = new GregorianCalendar();
        return service.insert(coments);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Coments> update(@PathVariable("id") Long id,
            @RequestBody Coments coments) {
        if (!id.equals(coments.getId()))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.update(coments));
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.remove(id);
    }
    
    @GetMapping("/tatuador")
    public List<Coments> findByTatuador(@RequestParam("tat") Tatuador tatuador) {
        return service.findByTatuador(tatuador);
    }
    
    @GetMapping("/cliente")
    public List<Coments> findByCliente(@RequestParam("cliente") User cliente) {
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