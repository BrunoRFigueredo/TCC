/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.pictatture.models;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "coments",schema = "public")
@SequenceGenerator(name = "coments_seq", sequenceName = "public.coments_seq", allocationSize = 10)
public class Coments {
    
    
    @Id
    @Column(name = "id_coment")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coments_seq")
    private Long id;
    
    @NotNull(message = "{com.bruno.pictatture.models.Orcamento.dataTat.NotNull}")
    @Column(name = "data_coment")
    private String dataComent;
    
    @NotNull(message = "O comentário não pode ficar em branco")
    @Size(max = 500)
    @Column(name = "coment",nullable = false)
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "id_tatuador")
    private Tatuador tatuador;
    
    @ManyToOne
    @JoinColumn(name = "id_users")
    private User cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataComent() {
        return dataComent;
    }

    public void setDataComent(String dataComent) {
        this.dataComent = dataComent;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Tatuador getTatuador() {
        return tatuador;
    }

    public void setTatuador(Tatuador tatuador) {
        this.tatuador = tatuador;
    }

    public User getCliente() {
        return cliente;
    }

    public void setCliente(User cliente) {
        this.cliente = cliente;
    }

 
    
    
    
   
    
    
    
}
