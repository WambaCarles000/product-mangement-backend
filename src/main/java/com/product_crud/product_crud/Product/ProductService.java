package com.product_crud.product_crud.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Récupérer tous les produits
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // Créer un produit
    public String createProduct(Product product) {
        productRepository.save(product);
        return "Produit créé avec succès !";
    }


    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }


    // Supprimer un produit par ID
    public boolean deleteProductById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    
    // Mettre à jour un produit par ID
    public boolean updateProductById(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            productRepository.save(product);
            return true;
        }
        return false;
    }
}
