package com.example.studentms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.studentms.entity.Product;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p WHERE "+
			"p.name LIKE CONCAT('%',:query,'%')"+
			"OR p.description LIKE CONCAT('%',:query,'%')")
	List<Product> searchProducts(String query);
	
	Optional<Product> findById(Long id);
}
