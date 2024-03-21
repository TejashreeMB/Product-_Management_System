package com.example.pms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.pms.dto.ProductDTO;
import com.example.pms.entity.Product;
import com.example.pms.utility.ResponseStructure;

public interface ProductService {

	ResponseEntity<ResponseStructure<Product>> findProduct(int productId);

	ResponseEntity<ResponseStructure<Product>> saveProduct(ProductDTO productdto);

	ResponseEntity<ResponseStructure<Product>> updateProduct(int productId, ProductDTO productdto);

	ResponseEntity<ResponseStructure<List<Product>>> findAllProducts();

	
}
