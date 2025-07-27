package dev.java.ecommerce.basketService.service;

import dev.java.ecommerce.basketService.client.PlatziStoreClient;
import dev.java.ecommerce.basketService.client.response.PlatziProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    public ProductService(PlatziStoreClient platziStoreClient) {
        this.platziStoreClient = platziStoreClient;
    }

    public List<PlatziProductResponse> getAllProducts(){
            return platziStoreClient.getAllProducts();
    }

    public PlatziProductResponse getProductById(Long id){
        return platziStoreClient.getProductById(id);
    }
}
