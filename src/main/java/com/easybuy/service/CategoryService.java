package com.easybuy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easybuy.model.Category;
import com.easybuy.repository.CategoryRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id){
        return categoryRepository.findById(id).get();
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

    public Category patchCategory(Long id, Category parcialCategory){
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            
            Category categoryToUpdate = categoryOptional.get();
            
            if (parcialCategory.getDescription() != null) {
                categoryToUpdate.setDescription(parcialCategory.getDescription());
            }
            return categoryRepository.save(categoryToUpdate);
        } else {
            return null;
        }
    }
}
