package com.optimal.task.users;


public class ResponseUser {
    private Integer id;
    private String role;
    private String token;
    private Boolean success;
    private String username;
    private String surname;
    private String name;
    private String fatherName;

    public ResponseUser(String username) {
        this.username = username;
    }

    public ResponseUser(Integer id, String role, String token, Boolean success, String text) {
        this.id = id;
        this.role = role;
        this.token = token;
        this.username = text;
        this.success = success;
    }


    public ResponseUser() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }


}
