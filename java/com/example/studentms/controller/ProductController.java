package com.example.studentms.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.studentms.entity.Product;
import com.example.studentms.service.ProductService;


@Controller
public class ProductController {
	
	@Autowired
	private ProductService productservice;
	
	@GetMapping("/")
	public String viewHomePage(Model model)
	{
		model.addAttribute("allprolist",productservice.getAllProduct());
		return "index";
	}
	
	@GetMapping("/addnew")
	public String addNewProduct(Model model)
	{
		Product product=new Product();
		model.addAttribute("product",product);
		return "newProduct";
	}
	
	@PostMapping("/save")
	public String saveProduct(@ModelAttribute("product") Product product)
	{
		productservice.saveProduct(product);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String updateForm(@PathVariable(value="id") long id,Model model)
	{
		Product product=productservice.getProductById(id);
		model.addAttribute("product",product);
		return "update";
	}
	
	@GetMapping("/deleteProduct/{id}")
	public String deletebyId(@PathVariable(value="id") long id,Model model)
	{
		productservice.deleteProduct(id);
		return "redirect:/";
	}
	
	@GetMapping("/search")
	public String searchProducts(Model model,@RequestParam("keyword") String query)
	{
		List<Product> list=productservice.searchProducts(query);
		model.addAttribute("allprolist",list);
		model.addAttribute("keyword",query);
		return "index";
	}
}
