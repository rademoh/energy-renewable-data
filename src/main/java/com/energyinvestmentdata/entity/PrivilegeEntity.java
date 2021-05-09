package com.energyinvestmentdata.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Rabiu Ademoh
 */
@Entity( name = "privilege")
public class PrivilegeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    //@ManyToMany(mappedBy = "privileges")
    //private Collection<RoleEntity> roles;

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

 /*   public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }*/
}