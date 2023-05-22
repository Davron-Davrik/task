package com.optimal.task.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.optimal.task.group.GroupEntity;
import com.optimal.task.mark.Mark;
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
    private Long id;

    private String name;
    private boolean state = true;

    @Column(unique = true)
    private String username;
    private String password;

    @JsonIgnore
    @ManyToMany
    private List<Role> roles;

    @ManyToOne
    private GroupEntity groupEntity;

    @OneToMany(mappedBy = "student")
    private List<Mark> markList;


    @UpdateTimestamp
    private Timestamp updateDate;

    @CreationTimestamp
    private Timestamp createDate;


    public Users(String name, String username, String password, List<Role> roles, GroupEntity groupEntity) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.groupEntity = groupEntity;
    }
}
