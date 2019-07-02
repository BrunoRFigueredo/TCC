package com.bruno.pictatture.models;

import com.bruno.pictatture.core.IEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;


@Entity
@Table(name = "USERS", schema = "chl_owner")
@SequenceGenerator(name = "users_seq", sequenceName = "chl_owner.users_seq", allocationSize = 1, schema = "chl_owner")
@JsonIgnoreProperties({"username","authorities","role","accountNonExpired", "accountNonLocked", "credentialsNonExpired","enabled"})
public class User implements UserDetails, IEntity<Long> {

    @Id
    @Column(name = "id_users")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    private Long id;

    @NotNull(message = "O nome do usuário não pode ser nulo")
    @Column(name = "NAME")
    @Length(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String name;

    @NotNull(message = "O e-mail do usuário não pode ser nulo")
    @Email(message = "Deve ser informado um e-mail válido")
    @Length(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
    @Column(name = "EMAIL")
    private String email;

    @NotNull(message = "A senha do usuário não pode ser nula")
    @Column(name = "PASSWORD")
    private String password;

    @Transient
    private String matchPassword;

    @Column(name = "ENABLED")
    private Boolean enabled = Boolean.TRUE;

    @ManyToOne
    @JoinColumn(name = "I_ROLES")
    @NotNull(message = "A role do usuário deve ser informada")
    private Role role;
    
    @JoinColumn(name = "id_cliente")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Cliente cliente = new Cliente();
    
    @JoinColumn(name = "id_tatuador",referencedColumnName = "id_tatuador", nullable = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Tatuador tatuador = new Tatuador();
    
    @JoinColumn(name = "id_imagem")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Imagem imagem;
    
   
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchPassword() {
        return matchPassword;
    }

    public void setMatchPassword(String matchPassword) {
        this.matchPassword = matchPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tatuador getTatuador() {
        return tatuador;
    }

    public void setTatuador(Tatuador tatuador) {
        this.tatuador = tatuador;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    
}
