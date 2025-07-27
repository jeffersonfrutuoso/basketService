package dev.java.ecommerce.basketService.service;

import dev.java.ecommerce.basketService.client.PlatziStoreClient;
import dev.java.ecommerce.basketService.client.response.PlatziProductResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    public ProductService(PlatziStoreClient platziStoreClient) {
        this.platziStoreClient = platziStoreClient;
    }

    @Cacheable(value = "products")
    public List<PlatziProductResponse> getAllProducts(){
            return platziStoreClient.getAllProducts();
    }

    @Cacheable(value = "products", key =  "#id")
    public PlatziProductResponse getProductById(Long id){
        return platziStoreClient.getProductById(id);
    }
}
