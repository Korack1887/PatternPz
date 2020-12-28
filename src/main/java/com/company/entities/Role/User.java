package com.company.entities.Role;



public class User {
    int id;
    String login;
    String pass;
    Role role=Role.USER;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User() {
        id=0;
        login="";
        pass="";
        role=Role.USER;
    }

    public static class Builder {
        private User user;

        public Builder() {
            user = new User();
        }

        public User.Builder id(int id) {
            user.id = id;
            return this;
        }
        public User.Builder login(String login) {
            user.login = login;
            return this;
        }
        public User.Builder pass(String pass) {
            user.pass = pass;
            return this;
        }
        public User.Builder role(Role name) {
            user.role = name;
            return this;
        }
        public User build(){
            return user;
        }
    }
}
