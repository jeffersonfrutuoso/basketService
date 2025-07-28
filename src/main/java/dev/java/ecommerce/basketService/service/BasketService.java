package dev.java.ecommerce.basketService.service;

import dev.java.ecommerce.basketService.client.response.PlatziProductResponse;
import dev.java.ecommerce.basketService.controller.request.BasketRequest;
import dev.java.ecommerce.basketService.domain.BasketEntity;
import dev.java.ecommerce.basketService.domain.ProductEntity;
import dev.java.ecommerce.basketService.domain.Status;
import dev.java.ecommerce.basketService.reposiroty.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;
    private final ProductService productService;

    public BasketEntity getBasketById(String id){
        return basketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("basket not found"));
    }

    public BasketEntity createdBasket(BasketRequest basketRequest){

        basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN)
                .ifPresent(basket -> {
                    throw new IllegalArgumentException("there is already open basket for this client");
                });

        List<ProductEntity> products = new ArrayList<>();
        basketRequest.products().forEach(productRequest -> {
            PlatziProductResponse platziProductResponse = productService.getProductById(productRequest.id());
            products.add(ProductEntity.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(productRequest.quantity())
                    .build());
        });
            BasketEntity basket = BasketEntity.builder()
                    .client(basketRequest.clientId())
                    .status(Status.OPEN)
                    .products(products)
                    .build();

            basket.calculateTotalPrice();
            return basketRepository.save(basket);
    }



}
