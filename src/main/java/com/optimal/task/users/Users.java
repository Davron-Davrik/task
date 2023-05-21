package com.optimal.task.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.optimal.task.role.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@Entity
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean state = true;
    private boolean delete = false;

    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    @Column(unique = true)
    private String username;
    private String password;
    private String phoneNumber;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;





    public Users(boolean state, boolean delete, String username, String password, List<Role> roles) {
        this.state = state;
        this.delete = delete;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Users() {
    }

    public Users(Integer id, String name, String username, String password, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Users(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Users(String name, boolean state, boolean delete, String username, String password, String phoneNumber, List<Role> roles) {
        this.name = name;
        this.state = state;
        this.delete = delete;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }


}
