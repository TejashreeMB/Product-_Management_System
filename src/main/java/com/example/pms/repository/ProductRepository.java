package com.example.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pms.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
