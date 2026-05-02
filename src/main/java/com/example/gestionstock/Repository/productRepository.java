package com.example.gestionstock.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.gestionstock.model.product;
import java.util.List;

@Repository
public interface productRepository extends JpaRepository<product, Long> {
    
    // Requête personnalisée : obtenir tous les produits en stock
    @Query("SELECT p FROM product p WHERE p.stock > 0")
    List<product> getProducts();

    // Trouver des produits par nom (recherche)
    List<product> findByNameContainingIgnoreCase(String name);

    // Trouver des produits par catégorie
    List<product> findByCategory(String category);

    // Trouver des produits dont le stock est inférieur à un certain niveau (alerte stock)
    List<product> findByStockLessThan(Integer stockLevel);
}
