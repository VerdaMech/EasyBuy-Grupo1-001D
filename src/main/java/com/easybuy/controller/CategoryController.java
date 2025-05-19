package com.easybuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easybuy.model.Category;
import com.easybuy.service.CategoryService;

@RestController
@RequestMapping("/api/v1/Categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> listar(){
        List<Category> listaCategorias = categoryService.findAll();
        if(listaCategorias.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaCategorias);
    }

    @PostMapping
    public ResponseEntity<Category> guardar(@RequestBody Category category){
        Category NewCategory = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(NewCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> buscar(@PathVariable Long id){
        try{
            Category category = categoryService.findById(id);
            return ResponseEntity.ok(category);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{}")
    public ResponseEntity<Category> actualizar(@PathVariable Long id, @RequestBody Category category){
        try{
            categoryService.save(category);
            return ResponseEntity.ok(category);
        }catch( Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Category> patchCategory(@PathVariable Long id, @RequestBody Category partialCategory) {
        try {
            Category updateCategory = categoryService.patchCategory(id, partialCategory);
            return ResponseEntity.ok(updateCategory);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try{
            categoryService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
