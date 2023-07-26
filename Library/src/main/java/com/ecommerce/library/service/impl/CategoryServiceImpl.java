package com.ecommerce.library.service.impl;

import com.ecommerce.library.model.Category;
import com.ecommerce.library.repository.CategoryRepository;
import com.ecommerce.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        try {
            Category categorySave=new Category(category.getName());
            return categoryRepository.save(categorySave);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Category findById(Long id) {

        return categoryRepository.findById(id).get();
    }

    @Override
    public Category update(Category category) {
        Category categoryUpdate=null;
        try {
            categoryUpdate=categoryRepository.findById(category.getId()).get();
            categoryUpdate.setName(category.getName());
            categoryUpdate.setIs_activated(category.getIs_activated());
            categoryUpdate.setIs_deleted(category.getIs_deleted());
        }catch (Exception e){
            e.printStackTrace();
        }
        return categoryRepository.save(categoryUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Category category=categoryRepository.getById(id);
        category.setIs_deleted(true);
        category.setIs_activated(false);
        categoryRepository.save(category);
    }

    @Override
    public void enableById(Long id) {
        Category category=categoryRepository.getById(id);
        category.setIs_activated(true);
        category.setIs_deleted(false);
        categoryRepository.save(category);
    }

    @Override
    public List<Category> findAllByIsActivated() {
        return categoryRepository.findAllByIsActivated();
    }
}
