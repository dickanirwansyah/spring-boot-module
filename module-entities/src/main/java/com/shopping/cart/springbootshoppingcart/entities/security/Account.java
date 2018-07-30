package com.shopping.cart.springbootshoppingcart.entities.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

    private static final Long serialVersionUID = -2054386655979281969L;

    public static final String ROLE_MANAGER = "ROLE_MANAGER";

    public static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "encryptedpassword", nullable = false)
    private String encryptedpassword;

    @Column(name = "active", length = 1, nullable = false)
    private boolean active;

    @Column(name = "userrole", nullable = false)
    private String userrole;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getEncryptedpassword(){
        return encryptedpassword;
    }

    public void setEncryptedpassword(String encryptedpassword){
        this.encryptedpassword = encryptedpassword;
    }

    public boolean isActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public String getUserrole(){
        return userrole;
    }

    public void setUserrole(String userrole){
        this.userrole = userrole;
    }
}
