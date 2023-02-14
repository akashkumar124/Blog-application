package com.akash.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.blog.payloads.ApiResonse;
import com.akash.blog.payloads.CategoryDto;
import com.akash.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categorySeriveces;
	
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categorDto){
		
		CategoryDto cDto=this.categorySeriveces.createCategory(categorDto);
		
		return new ResponseEntity<CategoryDto>(cDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{cId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categorDto, @PathVariable Integer cId){
		
		
		
		CategoryDto uDto=this.categorySeriveces.updateCategory(categorDto, cId);
		
		return new ResponseEntity<CategoryDto>(uDto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{cId}")
	public ResponseEntity<ApiResonse> updateCategory(@PathVariable Integer cId){
		
		
		
		this.categorySeriveces.deleteCategory(cId);
		
		return new ResponseEntity<ApiResonse>(new ApiResonse("Category deleted Succesfull", true),HttpStatus.OK);
	}
	
	@GetMapping("/{cId}")
	public ResponseEntity<CategoryDto> getCategorybyId(@PathVariable Integer cId){
		
		
		
		CategoryDto sCat=this.categorySeriveces.singleCategory(cId);
		
		return new ResponseEntity<CategoryDto>(sCat,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		
		
		
		List<CategoryDto> sCat=this.categorySeriveces.getAll();
		
		return new ResponseEntity<List<CategoryDto>>(sCat,HttpStatus.OK);
	}

	

}
