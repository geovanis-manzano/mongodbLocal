package com.example.mongodbLocal.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.mongodbLocal.models.Product;
import com.example.mongodbLocal.repositories.IProductRepository;

@Service
public class ProductService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private IProductRepository productRepository;
	
	@Async
	public List<Product> getAllProducts() {
		LOGGER.info("*** Getting all products ***");
		LOGGER.info("*** Execute method asynchronously: " + Thread.currentThread().getName());
		
		ThreadLocal<Integer> threadLocalValue = new ThreadLocal<>();
		threadLocalValue.set(1);
		Integer result = threadLocalValue.get();
		LOGGER.info("*** ThreadLocal: " + result);	
		
		return productRepository.findAll();
	}
	
	public Product save(Product product) {
		LOGGER.info("*** Saving a product ***");
		return productRepository.save(product);
	}
	
	public Product update(Product product) {
		return productRepository.save(product);
	}
	
	public Product updateParcial(String id, String desc) {
		Product product = productRepository.findById(id).get();
		product.setDescription(desc);
		return productRepository.save(product);
	}
	
	public Boolean delete(String id) {
		try {
			productRepository.deleteById(id);
			return true;
		}catch(Exception err){
			return false;
		}
	}
	
	public List<Product> getAllProductsByName(String name) {
		return productRepository.findByName(name);
	}
	
	public List<Product> getAllProductsByPrice(String price) {
		return productRepository.findByPrice(price);
	}
	
	public List<Product> getAllProductsByNameAndPrice(String name, String price) {
		return productRepository.findByNameAndPrice(name, price);
	}

	public List<Product> getAllProductsByNameOrPrice(String name, String price) {		
		return productRepository.findByNameOrPrice(name, price);
	}
}
