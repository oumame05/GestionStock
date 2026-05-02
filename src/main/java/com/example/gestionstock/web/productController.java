package com.example.gestionstock.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.gestionstock.model.product;
import com.example.gestionstock.service.productService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*") // Permettre les appels depuis n'importe quelle origine (pour le développement)
public class productController {

    @Autowired
    private productService productService;

    // GET: Obtenir tous les produits
    @GetMapping
    public List<product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET: Obtenir un produit par ID
    @GetMapping("/{id}")
    public ResponseEntity<product> getProductById(@PathVariable Long id) {
        Optional<product> optionalProduct = productService.getProductById(id);
        return optionalProduct.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET: Obtenir les produits en stock
    @GetMapping("/in-stock")
    public List<product> getProductsInStock() {
        return productService.getProductsInStock();
    }

    // GET: Rechercher des produits par nom
    @GetMapping("/search")
    public List<product> searchProducts(@RequestParam String name) {
        return productService.searchProductsByName(name);
    }

    // GET: Obtenir les produits par catégorie
    @GetMapping("/category/{category}")
    public List<product> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    // GET: Obtenir les produits avec stock faible
    @GetMapping("/low-stock/{stockLevel}")
    public List<product> getLowStockProducts(@PathVariable Integer stockLevel) {
        return productService.getLowStockProducts(stockLevel);
    }

    // POST: Ajouter un nouveau produit
    @PostMapping
    public product addProduct(@RequestBody product p) {
        return productService.saveProduct(p);
    }

    // PUT: Mettre à jour un produit existant
    @PutMapping("/{id}")
    public ResponseEntity<product> updateProduct(@PathVariable Long id, @RequestBody product productDetails) {
        product updatedProduct = productService.updateProduct(id, productDetails);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Supprimer un produit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
