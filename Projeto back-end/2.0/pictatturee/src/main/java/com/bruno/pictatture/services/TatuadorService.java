
package com.bruno.pictatture.services;

import com.bruno.pictatture.models.Imagem;
import com.bruno.pictatture.models.Tatuador;
import com.bruno.pictatture.repository.TatuadorRepository;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TatuadorService{
    
    @Autowired
    private TatuadorRepository repository;
    
    public List<Tatuador> findAll() {
        return repository.findAll();
    }
    
     public Tatuador storeFile(MultipartFile file,Tatuador tatuador) throws IOException{
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

     
           
            Imagem dbFile = new Imagem(file.getBytes(),fileName, file.getContentType());
            tatuador.getImagem().add(dbFile);
            return repository.save(tatuador);

    }
    
    public Tatuador insert(Tatuador cliente) {
        repository.save(cliente);
        return cliente;
    }
    
    public Tatuador findById(Long id) {
        return repository.findById(id).get();
    }
    
    public Tatuador update(Tatuador cliente) {
        return repository.save(cliente);
    }
    
    public void remove(Long id) {
        repository.deleteById(id);
    }
    
    public Tatuador findBynomeSocial(String nomeSocial) {
        return repository.findBynomeSocial(nomeSocial);
    }
    
}
