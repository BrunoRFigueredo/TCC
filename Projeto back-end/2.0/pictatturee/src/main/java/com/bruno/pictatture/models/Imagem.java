package com.bruno.pictatture.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "imagens",schema = "public")
@SequenceGenerator(name = "imagens_seq", sequenceName = "public.imagens_seq", allocationSize = 10)
public class Imagem implements Serializable {

    @Id
    @Column(name = "id_imagem")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imagens_seq")
    private Long id;
    @Column(name = "url_imagem")
    private byte[] data;
    @Column
    private String fileName;
    @Column
    private String fileType;
  
    
    
    public Imagem() {
    
    }

    public Imagem(byte[] data, String fileName, String fileType) {
        this.data = data;
        this.fileName = fileName;
        this.fileType = fileType;
   
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    
    
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] url) {
        this.data = url;
    }



}
