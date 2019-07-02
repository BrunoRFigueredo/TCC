package com.bruno.pictatture.models;



import com.bruno.pictatture.core.IEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "tatuador",schema = "public")
@SequenceGenerator(name = "tatuador_seq", sequenceName = "public.tatuador_seq", allocationSize = 1,schema = "public")
public class Tatuador implements IEntity<Long> {

    @Id
    @Column(name = "id_tatuador")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tatuador_seq")
    private Long id;
    
    @Column(name = "nm_social_tatuador")
    private String nomeSocial;
    @Column(name = "nm_etd_tatuador", nullable = false, length = 60)
    private String nomeEstudio;
    
     @Column(name = "descricao", length = 500)
    private String descricao;
    
    @Column(name = "id_imagem",nullable = true)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Imagem> imagem;
    

    @JoinColumn(name = "id_endereco",nullable = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;
    
    
    @Column(name = "validacao")
    private Boolean enabled = Boolean.FALSE;
    

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public String getNomeEstudio() {
        return nomeEstudio;
    }

    public void setNomeEstudio(String nomeEstudio) {
        this.nomeEstudio = nomeEstudio;
    }

    public List<Imagem> getImagem() {
        return imagem;
    }

    public void setImagem(List<Imagem> imagem) {
        this.imagem = imagem;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    

 
  

    
    
}
