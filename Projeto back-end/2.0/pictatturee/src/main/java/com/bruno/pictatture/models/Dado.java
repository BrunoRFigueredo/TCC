package com.bruno.pictatture.models;

import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "dados",schema = "public")
@SequenceGenerator(name = "dados_seq", sequenceName = "public.dados_seq", allocationSize = 10)
public class Dado {

    @Id
    @Column(name = "id_dados")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dados_seq")
    private Long id;
    
    @NotNull(message = "{com.bruno.pictatture.models.Dados.lugarTat.NotNull}")
    @Column(name = "lugar_dados", length = 30, nullable = false)
    private String lugarTat;
     
    @Column(name = "descricao_dados", length = 500, nullable = true)
    private String descricao;
    
    @NotNull(message = "{com.bruno.pictatture.models.Dados.comprimentoTat.NotNull}")
    @Digits(integer = 10,fraction = 5)
    @Column(name = "comprimento_tat_dados", precision = 15, scale = 5, nullable = false)
    private BigDecimal comprimentoTat;
    
    @Digits(integer = 10,fraction = 5)
    @Column(name = "comprimento_lugar_dados", precision = 15, scale = 5)
    private BigDecimal comprimentoLugar;
    
    @Column(name = "data_tat_dados", nullable = false)
    private String dataTat;
    
    @ManyToOne
    @JoinColumn(name = "id_tatuador")
    private Tatuador tatuador;
    
    @ManyToOne
    @JoinColumn(name = "id_users", nullable = false)
    private User cliente;

    
    @JoinColumn(name = "id_imagem", nullable = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)
    private Imagem imagem;
     
    
    @Column(name = "validacao")
    private Boolean enabled = Boolean.FALSE;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLugarTat() {
        return lugarTat;
    }

    public void setLugarTat(String lugarTat) {
        this.lugarTat = lugarTat;
    }

    public BigDecimal getComprimentoTat() {
        return comprimentoTat;
    }

    public void setComprimentoTat(BigDecimal comprimentoTat) {
        this.comprimentoTat = comprimentoTat;
    }

    public BigDecimal getComprimentoLugar() {
        return comprimentoLugar;
    }

    public void setComprimentoLugar(BigDecimal comprimentoLugar) {
        this.comprimentoLugar = comprimentoLugar;
    }

    public String getDataTat() {
        return dataTat;
    }

    public void setDataTat(String dataTat) {
        this.dataTat = dataTat;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    
   

    public User getCliente() {
        return cliente;
    }

    public void setCliente(User cliente) {
        this.cliente = cliente;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public Tatuador getTatuador() {
        return tatuador;
    }

    public void setTatuador(Tatuador tatuador) {
        this.tatuador = tatuador;
    }

   
    

    
   

}
