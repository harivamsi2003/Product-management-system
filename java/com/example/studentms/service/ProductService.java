package com.example.studentms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentms.entity.Product;
import com.example.studentms.repository.ProductRepository;

@RestController
public class ProductService {
	@Autowired
	private ProductRepository productrepository;
	
	public List<Product> getAllProduct()
	{
		return productrepository.findAll();
	}
	
	public List<Product> searchProducts(String query)
	{
		if (query == null || query.trim().isEmpty()) {
            return productrepository.findAll();
        }
		try {
			Long id=Long.parseLong(query);
			return productrepository.findById(id).stream().toList();
		}
		catch(NumberFormatException e){
			return productrepository.searchProducts(query);
		}
	}
	
	public Product saveProduct(Product product)
	{
		return productrepository.save(product);
	}
	
	public Product getProductById(Long id)
	{
		Optional<Product> op=productrepository.findById(id);
		Product product=null;
		if(op.isPresent())
		{
			product=op.get();
		}
		else throw new RuntimeException("Employee not found for id : " + id);
		return product;
	}
	
	public Product updateProduct(Long id,Product upProduct)
	{
		Optional<Product> existproduct=productrepository.findById(id);
		if(existproduct.isPresent())
		{
			Product product=existproduct.get();
			product.setName(upProduct.getName());
			product.setDescription(upProduct.getDescription());
			return productrepository.save(product);
		}
		else
		{
			throw new RuntimeException("Product not found");
		}
	}
	
	public void deleteProduct(Long id)
	{
		Optional<Product> existproduct=productrepository.findById(id);
		if(existproduct.isPresent())
		{
			productrepository.deleteById(id);
		}
		else
		{
			throw new RuntimeException("Product not found");
		}
	}
}
