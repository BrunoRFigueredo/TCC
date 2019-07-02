package com.bruno.pictatture.models;

import com.bruno.pictatture.core.IEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "enderecos",schema = "public")
@SequenceGenerator(name = "enderecos_seq", sequenceName = "public.enderecos_seq", allocationSize = 1)
public class Endereco implements IEntity<Long> {

    @Id
    @Column(name = "id_endereco")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enderecos_seq")
    private Long id;
    @NotNull(message = "{com.bruno.pictatture.models.Endereco.cidade.NotNull}")
    @Column(name = "cidade_endereco", nullable = false)
    private String cidade;
    @Column(name = "bairro_endereco", nullable = false)
    private String bairro;
    @NotNull(message = "{com.bruno.pictatture.models.Endereco.unidadeFederativa.NotNull}")
    @Size(max = 2,message = "{com.bruno.pictatture.models.Endereco.unidadeFederativa.Size}")
    @Column(name = "uf_endereco", nullable = false,length = 2)
    private String unidadeFederativa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUnidadeFederativa() {
        return unidadeFederativa;
    }

    public void setUnidadeFederativa(String unidadeFederativa) {
        this.unidadeFederativa = unidadeFederativa;
    }

    

       
    
    
    
}
