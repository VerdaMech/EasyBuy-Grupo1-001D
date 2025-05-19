package com.easybuy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easybuy.model.Product;
import com.easybuy.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        return productRepository.findById(id).get();
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

    public Product patchProduct(Long id, Product parcialProduct){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            
            Product productToUpdate = productOptional.get();
            
            if (parcialProduct.getName_product() != null) {
                productToUpdate.setName_product(parcialProduct.getName_product());
            }

            if(parcialProduct.getPrice() != 0) {
                productToUpdate.setPrice(parcialProduct.getPrice());
            }

            if(parcialProduct.getStock() != 0) {
                productToUpdate.setStock(parcialProduct.getStock());
            }
            return productRepository.save(productToUpdate);
        } else {
            return null;
        }
    }
}
