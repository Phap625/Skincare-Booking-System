package edu.uth.jpa.models;
import java.util.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "permission", schema = "jpa")
public class Permission {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Codes", length = 50)
    private String codes;

    @Column(name = "permission_name", length = 100)
    private String permissionName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @ManyToMany(mappedBy = "permissions")
    private Set<User> users=new HashSet<>();
    public Set<User> getUsers() {return users;}
    public void setUsers(Set<User> users) {this.users = users;}

}