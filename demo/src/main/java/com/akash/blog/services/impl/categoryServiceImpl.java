package com.akash.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.blog.entity.Category;
import com.akash.blog.exception.ResourceNotFoundException;
import com.akash.blog.payloads.CategoryDto;
import com.akash.blog.repositories.CategoryRepo;
import com.akash.blog.services.CategoryService;

@Service
public class categoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		
		Category cat= this.modelMapper.map(categoryDto, Category.class);
		Category savedCat =this.categoryRepo.save(cat);
		
		return this.modelMapper.map(savedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat= this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryID", categoryId));
		
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		
		Category updatCat = this.categoryRepo.save(cat);
		
		
		return this.modelMapper.map(updatCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryID", categoryId));
		
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto singleCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryID", categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAll() {
		// TODO Auto-generated method stub
		List<Category> allCat=this.categoryRepo.findAll();
		
		List<CategoryDto> CatDto= allCat.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return CatDto;
	}
	
	


	
}
