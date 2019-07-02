package com.bruno.pictatture.models;

import com.bruno.pictatture.core.ChallengeConstants;
import static com.bruno.pictatture.core.ChallengeConstants.GENERATOR_SEQUENCE;

import com.bruno.pictatture.core.IEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;



@Entity
@Table(name = "ROLES", schema = ChallengeConstants.SCHEMA)
@GenericGenerator(name = "SEQ_ROLES", strategy = GENERATOR_SEQUENCE,
        parameters = {@Parameter(name = "sequence", value = "SEQ_ROLES"),
                @Parameter(name = "schema", value = ChallengeConstants.SCHEMA)})
public class Role implements IEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROLES")
    private Long id;

    @NotNull(message = "O nome da ROLE não pode ser nulo")
    @Column(name = "NAME")
    private String name;
    
    
    
    
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

   
    
}
