package com.product_crud.product_crud.Product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*") // Autorise les requêtes CORS
public class ProductController {

    private  ProductService productService;

    // Injection des dep
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Liste pour simulation si nécessaire (optionnel)
    private List<Product> products = new ArrayList<>();

    // Récupérer tous les produits
    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    // Créer un produit
    @PostMapping
    public String createProduct(@RequestBody Product product) {
        productService.createProduct(product);
        return "Le produit a été créé avec succès";
    }


    
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        return productService.findProductById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // Supprimer un produit par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProductById(id);

        if (isDeleted) {
            return new ResponseEntity<>("Produit supprimé avec succès", HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Mettre à jour un produit par ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProductById(@PathVariable Long id, @RequestBody Product updatedProduct) {
        boolean isUpdated = productService.updateProductById(id, updatedProduct);

        if (isUpdated) {
            return new ResponseEntity<>("Produit mis à jour avec succès", HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
