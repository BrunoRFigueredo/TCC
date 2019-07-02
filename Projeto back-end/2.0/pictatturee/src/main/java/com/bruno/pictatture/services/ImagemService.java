package com.bruno.pictatture.services;

import com.bruno.pictatture.models.Imagem;
import com.bruno.pictatture.models.Tatuador;
import com.bruno.pictatture.repository.ImagemRepository;
import com.bruno.pictatture.validators.MyFileNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
@Service
public class ImagemService{

    @Autowired
    private ImagemRepository repository;
    
    
    public Imagem storeFile(MultipartFile file) throws IOException{
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

      
            Imagem dbFile = new Imagem(file.getBytes(),fileName, file.getContentType());

            return repository.save(dbFile);
      
    }

    public Imagem getFile(Long fileId) {
        return repository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("O arquivo com o id " + fileId + "não foi encontrado"));
    }
    
   
  
    
        public void remove(Long id) {
        repository.deleteById(id);
    }
}