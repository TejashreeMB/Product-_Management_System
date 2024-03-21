package com.example.pms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pms.dto.ProductDTO;
import com.example.pms.entity.Product;
import com.example.pms.service.ProductService;
import com.example.pms.utility.ErrorStructure;
import com.example.pms.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class ProductController {
	
	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@Operation(description = "The endpoint is used to fetch the product based on ID" , responses = {
			@ApiResponse(responseCode = "200",description = "Product Found"),
			@ApiResponse(responseCode = "404",description = "Product not found by given ID" ,content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class))
			})})
	@GetMapping("/products/{productId}")
	public ResponseEntity<ResponseStructure<Product>> findProduct(@PathVariable int  productId)
	{
		return productService.findProduct(productId);
	}

	@Operation(description = "The endpoint is used to save the details of Product" , responses = {
			@ApiResponse(responseCode = "200",description = "Product Saved Successfully"),
			@ApiResponse(responseCode = "400",description = "Bad Request" , content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class))
			})
	})
	@PostMapping("/products")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody ProductDTO productdto)
	{
		return productService.saveProduct(productdto);
	}
	
	
	
	@Operation(description = "The endpoint is used to update the products based on ID" , responses = {
			@ApiResponse(responseCode = "200",description = "Product Updated "),
		
	})
	@PutMapping("/products/{productId}")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@PathVariable int productId,@RequestBody ProductDTO productdto)
	{
		return productService.updateProduct(productId, productdto);
	}
	
	@GetMapping("/products")
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts()
	{
		return productService.findAllProducts();
	}

}
