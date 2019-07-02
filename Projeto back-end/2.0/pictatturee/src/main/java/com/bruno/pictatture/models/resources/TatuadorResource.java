package com.bruno.pictatture.models.resources;

import com.bruno.pictatture.models.Cliente;
import com.bruno.pictatture.models.Tatuador;
import com.bruno.pictatture.models.User;
import com.bruno.pictatture.services.TatuadorService;
import com.bruno.pictatture.validators.UploadFileResponse;
import java.io.IOException;
import java.util.List;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin
@RestController
@RequestMapping("/tatuador")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class TatuadorResource  {

     @Autowired
    private TatuadorService service;
     


    @GetMapping
    public List<Tatuador> findAll() {
        return service.findAll();
    }
    
    @GetMapping("/{id}")
    public Tatuador findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }
    
     @PostMapping(value = "/uploadFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("id") Tatuador tatuador) throws IOException{
        
        Tatuador d = service.storeFile(file,tatuador);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .toUriString();

        return new UploadFileResponse("", fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    
     @PostMapping
    public Tatuador insert(@RequestBody Tatuador dto) {
                return service.insert(dto);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Tatuador> update(@PathVariable("id") Long id,
            @RequestParam("tat") Tatuador tatuador) {
            tatuador.setId(id);
            tatuador.setEnabled(Boolean.TRUE);
        if (!id.equals(tatuador.getId()))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.update(tatuador));
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.remove(id);
    }
    
    @GetMapping("/nome")
    public Tatuador findBynomeSocial(@RequestParam("nomeSocial") String nomeSocial) {
        return service.findBynomeSocial(nomeSocial);
    }  
    
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<String> 
        handleValidationException(ConstraintViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
    
}
