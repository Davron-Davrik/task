package com.optimal.task.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.optimal.task.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean state;

    @Column(unique = true)
    private String username;
    private String password;

    @JsonIgnore
    @ManyToMany
    private List<Role> roles;


    @UpdateTimestamp
    private Timestamp updateDate;

    @CreationTimestamp
    private Timestamp createDate;


}
