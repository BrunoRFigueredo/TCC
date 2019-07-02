package com.bruno.pictatture.models;


import com.bruno.pictatture.core.IEntity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "clientes",schema = "public")
@SequenceGenerator(name = "clientes_seq", sequenceName = "public.clientes_seq", allocationSize = 1, schema = "public")
public class Cliente implements IEntity<Long> {

    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientes_seq")
    private Long id;
    
  
    @NotNull(message = "É necessário que o CPF esteja prenchido")
    @Size(min = 11,max = 15,message = "O CPF deve ter de 11 a 15 caracteres")
    @Column(name = "cpf_cliente", length = 20, unique = true, nullable = false)
    private String cpf;
    
    @Size(min = 8,max = 20,message = "{com.bruno.pictatture.models.Clientes.Size}")
    @Column(name = "tf_cliente", length = 20)
    private String telefone;
    
    
    @JoinColumn(name = "id_endereco")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;


    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

 
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
   

}
