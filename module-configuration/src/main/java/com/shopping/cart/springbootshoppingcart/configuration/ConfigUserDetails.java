package com.shopping.cart.springbootshoppingcart.configuration;

import com.shopping.cart.springbootshoppingcart.entities.security.Account;
import com.shopping.cart.springbootshoppingcart.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConfigUserDetails implements UserDetailsService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findByUsername(username);

        if (account == null){
            throw new UsernameNotFoundException("Maaf user "+username+" tidak terdapat di database.");
        }

        //add roles
        String role = account.getUserrole();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        grantedAuthorities.add(authority);

        boolean enabled = account.isActive();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails = new User(account.getUsername(),
                account.getEncryptedpassword(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, grantedAuthorities);

        return userDetails;
    }
}
