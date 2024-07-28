package store.mgmt.entity;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;


@Entity
@Table(name="ROLES", schema = "STOREDB")
public class Role{



    public void setRoleName(UserRole roleName) {
        this.roleName = roleName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_NAME")
    private UserRole roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public UserRole getRoleName() {
        return roleName;
    }
}
