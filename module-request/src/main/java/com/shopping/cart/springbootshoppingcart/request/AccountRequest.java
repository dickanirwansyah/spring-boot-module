package com.shopping.cart.springbootshoppingcart.request;

public class AccountRequest {

    private String accountUsername;

    private String accountPassword;

    private boolean accountActive;

    private String accountRole;

    public AccountRequest(){}

    public String getAccountUsername(){
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername){
        this.accountUsername = accountUsername;
    }

    public String getAccountPassword(){
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword){
        this.accountPassword = accountPassword;
    }

    public boolean isAccountActive(){
        return accountActive;
    }

    public void setAccountActive(boolean accountActive){
        this.accountActive = accountActive;
    }

    public String getAccountRole(){
        return accountRole;
    }

    public void setAccountRole(String accountRole){
        this.accountRole = accountRole;
    }
}
