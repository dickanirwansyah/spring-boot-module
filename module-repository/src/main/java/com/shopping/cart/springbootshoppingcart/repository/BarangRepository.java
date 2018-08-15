package com.shopping.cart.springbootshoppingcart.repository;

import com.shopping.cart.springbootshoppingcart.entities.Barang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarangRepository extends JpaRepository<Barang, String>{
}
