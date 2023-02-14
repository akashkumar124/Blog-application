package com.akash.blog.services;

import java.util.List;

import com.akash.blog.payloads.CategoryDto;

public interface CategoryService {
	
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	public void deleteCategory(Integer categoryId);
	
	public CategoryDto singleCategory( Integer categoryId);
	
	public List<CategoryDto> getAll();

}
