package com.example.mongodbLocal.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.mongodbLocal.models.Product;

@Repository
public interface IProductRepository extends MongoRepository<Product, String>{
	
	public abstract List<Product> findByName(String name);
	
	public abstract List<Product> findByPrice(String price);
	
	public abstract List<Product> findByNameAndPrice(String name, String price);
	
	public abstract List<Product> findByNameOrPrice(String name, String price);
}
