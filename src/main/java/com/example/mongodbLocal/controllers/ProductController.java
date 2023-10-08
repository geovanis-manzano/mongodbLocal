package com.example.mongodbLocal.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodbLocal.models.Product;
import com.example.mongodbLocal.services.ProductService;
import com.example.mongodbLocal.validation.ProductCreate;
import com.example.mongodbLocal.validation.ValidList;
import com.google.common.net.HttpHeaders;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping()
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@PostMapping()
	public Product save(@Validated(ProductCreate.class) @RequestBody Product product, HttpServletRequest request){
		String ip = request.getHeader(HttpHeaders.X_FORWARDED_FOR);		
		String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
			
		return productService.save(product);
	}
	
	@PostMapping("/test")
	public String saveTest(@Validated(ProductCreate.class) @RequestBody ValidList<Product> products){
		for (Product product : products) {
			return product.getName();
		}
		return "OK";
	}
	
	@PutMapping()
	public Product update(@RequestBody Product product) {
		return productService.save(product);
	}
	
	@PatchMapping("/{id}")
	public Product updateParcial(@PathVariable String id, @RequestParam String desc) {
		return productService.updateParcial(id, desc);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") String id) {
		Boolean ok = this.productService.delete(id);
		
		if(ok){
			return "Se elimino el producto: " + id;
		}else {
			return "No se pudo eliminar el producto: " + id;
		}
	}
	
	@GetMapping("/name/{name}")
	public List<Product> getAllProductsByName(@PathVariable String name){
		return productService.getAllProductsByName(name);
	}
	
	@GetMapping(path = "/price/{price}")
	public List<Product> getAllProductsByPrice(@PathVariable String price){
		return productService.getAllProductsByPrice(price);
	}
	
	@GetMapping(path = "test_and/name/{name}/price/{price}")
	public List<Product> getAllProductsByNameAndPrice(@PathVariable String name, @PathVariable String price){
		return productService.getAllProductsByNameAndPrice(name, price);
	}
	
	@GetMapping(path = "test_or/name/{name}/price/{price}")
	public List<Product> getAllProductsByNameOrPrice(@PathVariable String name, @PathVariable String price){
		return productService.getAllProductsByNameOrPrice(name, price);
	}
}
