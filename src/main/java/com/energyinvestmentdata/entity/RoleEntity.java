package com.energyinvestmentdata.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Rabiu Ademoh
 */
@Entity( name = "role")
public class RoleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

   // @ManyToMany(mappedBy = "roles")
    //private Collection<UserEntity> users;

    @ManyToMany( cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<PrivilegeEntity> privileges;

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

    public Collection<PrivilegeEntity> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<PrivilegeEntity> privileges) {
        this.privileges = privileges;
    }
}