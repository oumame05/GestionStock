package com.example.gestionstock.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.gestionstock.Repository.productRepository;
import com.example.gestionstock.model.product;

@Service
public class productService {
    
    @Autowired
    private productRepository productRepository;

    // --- Opérations de lecture (GET) ---
    
    public List<product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<product> getProductsInStock() {
        return productRepository.getProducts();
    }

    public Optional<product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<product> getLowStockProducts(Integer stockLevel) {
        return productRepository.findByStockLessThan(stockLevel);
    }

    // --- Opérations d'écriture (POST, PUT, DELETE) ---

    public product saveProduct(product p) {
        return productRepository.save(p);
    }

    public product updateProduct(Long id, product productDetails) {
        Optional<product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            product existingProduct = optionalProduct.get();
            // Mettre à jour les champs
            existingProduct.setName(productDetails.getName());
            existingProduct.setDescription(productDetails.getDescription());
            existingProduct.setPrice(productDetails.getPrice());
            existingProduct.setStock(productDetails.getStock());
            existingProduct.setImage(productDetails.getImage());
            existingProduct.setCategory(productDetails.getCategory());
            existingProduct.setBrand(productDetails.getBrand());
            existingProduct.setColor(productDetails.getColor());
            existingProduct.setSize(productDetails.getSize());
            
            return productRepository.save(existingProduct);
        }
        return null; // Ou lancer une exception personnalisée (ex: ProductNotFoundException)
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
