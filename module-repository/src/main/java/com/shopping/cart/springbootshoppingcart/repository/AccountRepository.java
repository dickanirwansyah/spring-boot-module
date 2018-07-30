package com.shopping.cart.springbootshoppingcart.repository;

import com.shopping.cart.springbootshoppingcart.entities.security.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String>{

    Account findByUsername(String username);
}
