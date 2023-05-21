package com.optimal.task.users;

public class SignInUser {
    private String login;
    private String password;

    public SignInUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public SignInUser() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
