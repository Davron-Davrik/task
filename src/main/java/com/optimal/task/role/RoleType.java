package com.optimal.task.role;


public enum RoleType {

    ADMIN(1, "ROLE_ADMIN"),
    STUDENT(2, "ROLE_STUDENT"),
    MENTOR(3, "ROLE_MENTOR");


    private Integer id;
    private String name;

    RoleType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
