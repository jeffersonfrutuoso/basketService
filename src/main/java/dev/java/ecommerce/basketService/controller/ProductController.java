package dev.java.ecommerce.basketService.controller;

import dev.java.ecommerce.basketService.client.response.PlatziProductResponse;
import dev.java.ecommerce.basketService.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<PlatziProductResponse>> getAll(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("{id}")
    public ResponseEntity<PlatziProductResponse> getAllById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }


}
