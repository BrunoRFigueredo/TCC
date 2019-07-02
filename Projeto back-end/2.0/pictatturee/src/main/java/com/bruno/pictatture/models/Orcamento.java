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

@Entity
@Table(name = "orcamentos",schema = "public")
@SequenceGenerator(name = "orcamentos_seq", sequenceName = "public.orcamentos_seq", allocationSize = 10)
public class Orcamento {

    @Id
    @Column(name = "id_orcamento")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orcamentos_seq")
    private Long id;
    
    @NotNull(message = "{com.bruno.pictatture.models.Orcamento.valorTatuagem.NotNull}")
    @Digits(integer = 10,fraction = 5)
    @Column(name = "valor_tat_orcamento", precision = 15, scale = 5, nullable = false)
    private BigDecimal valorTatuagem;
    
    @NotNull(message = "{com.bruno.pictatture.models.Orcamento.dataTat.NotNull}")
    @Column(name = "data_tat", nullable = false)
    private String dataTat;
    
    @Size(max = 500,message = "{com.bruno.pictatture.models.Orcamento.descricao.Size}")
    @Column(name = "descricao_orcamento")
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "id_tatuador", nullable = false)
    private Tatuador tatuador;
    
    @ManyToOne
    @JoinColumn(name = "id_users", nullable = false)
    private User cliente;
    
    @ManyToOne
    @JoinColumn(name = "id_dado", nullable = false)
    private Dado dado;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorTatuagem() {
        return valorTatuagem;
    }

    public void setValorTatuagem(BigDecimal valorTatuagem) {
        this.valorTatuagem = valorTatuagem;
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

    public Tatuador getTatuador() {
        return tatuador;
    }

    public void setTatuador(Tatuador tatuador) {
        this.tatuador = tatuador;
    }

    public User getUser() {
        return cliente;
    }

    public void setUser(User cliente) {
        this.cliente = cliente;
    }

    public Dado getDado() {
        return dado;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }

    
    
}
