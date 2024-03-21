package com.example.pms.serviceImpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.pms.dto.ProductDTO;
import com.example.pms.entity.Product;
import com.example.pms.exception.ProductNotFoundByIdException;
import com.example.pms.repository.ProductRepository;
import com.example.pms.service.ProductService;
import com.example.pms.utility.ResponseStructure;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepo;
	private ResponseStructure<Product> structure;
	private ResponseStructure<List<Product>> listStructure;

	public ProductServiceImpl(ProductRepository productRepo, ResponseStructure<Product> structure,ResponseStructure<List<Product>> listStructure) {
		this.productRepo = productRepo;
		this.structure = structure;
		this.listStructure = listStructure;
	}

	@Override
	public ResponseEntity<ResponseStructure<Product>> findProduct(int productId) {
		return productRepo.findById(productId)
				.map(product -> ResponseEntity.ok(
						structure.setStatusCode(HttpStatus.OK.value()).setMessage("Product Found").setData(product)))
				.orElseThrow(() -> new ProductNotFoundByIdException("Invalid Product"));
	}
	
//	@Override
//	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) {
//	  Product exProduct = productRepo.save(product);
//	  return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value()).setMessage("Object Saved").setData(exProduct));
//	}

//	@Override
//	public ResponseEntity<ResponseStructure<Product>> updateProduct(int productId, Product product) {
//		return productRepo.findById(productId)
//				.map(exProduct -> {
//					product.setProductId(exProduct.getProductId());
//					exProduct = productRepo.save(product);
//					return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
//							.setMessage("Product Updated..")
//							.setData(exProduct));
//				}).orElseThrow(()->new ProductNotFoundByIdException("Product Not Found.."));
//				
//	}

	@Override
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts() {
		List<Product> product = productRepo.findAll();
		if(!product.isEmpty()) {
			return ResponseEntity.ok(
					listStructure.setStatusCode(HttpStatus.OK.value())
					.setMessage("All Products Fetched")
					.setData(product));
		}else throw new ProductNotFoundByIdException("Product Not Found..");
		}

	@Override
	public ResponseEntity<ResponseStructure<Product>> saveProduct(ProductDTO productdto) {
		// mapping DTO into entity
		Product product = productRepo.save(mapToProduct(productdto,new Product()));
		return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
				.setMessage("Product Saved Successfully...")
				.setData(product));
	}

	private Product mapToProduct(ProductDTO productdto, Product product) {
		    product.setProductName(productdto.getProductName());
		    product.setProductBrand(productdto.getProductBrand());
		    product.setProductPrice(productdto.getProductPrice());
		    return product;
			}

//	private Product mapToProduct(ProductDTO productdto) {
//	   return Product.builder()
//			   .ProductName(ProductRequest.getProductName())
//			   .ProductBrand(ProductRequest.getProductBrand())
//			   .ProductPrice(ProductRequest.getProductPrice())
//			   .build();	   
//		}

	@Override
	public ResponseEntity<ResponseStructure<Product>> updateProduct(int productId, ProductDTO productdto) {
		Product product = mapToProduct(productdto,new Product());
		return productRepo.findById(productId)
				.map(exProduct -> {
					product.setProductId(exProduct.getProductId());
					exProduct = productRepo.save(product);
					return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
							.setMessage("Product Updated..")
							.setData(exProduct));
				}).orElseThrow(()->new ProductNotFoundByIdException("Product Not Found.."));
				
	}
	
	
	}


