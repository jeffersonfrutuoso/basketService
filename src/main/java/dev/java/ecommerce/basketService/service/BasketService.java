package dev.java.ecommerce.basketService.service;

import dev.java.ecommerce.basketService.client.response.PlatziProductResponse;
import dev.java.ecommerce.basketService.controller.request.BasketRequest;
import dev.java.ecommerce.basketService.controller.request.PaymentMethodRequest;
import dev.java.ecommerce.basketService.domain.BasketEntity;
import dev.java.ecommerce.basketService.domain.ProductEntity;
import dev.java.ecommerce.basketService.domain.Status;
import dev.java.ecommerce.basketService.exceptions.BusinessException;
import dev.java.ecommerce.basketService.exceptions.DataNotFoundException;
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
        return basketRepository.findById(id).orElseThrow(() -> new DataNotFoundException("basket not found"));
    }

    public BasketEntity createdBasket(BasketRequest basketRequest){

        basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN)
                .ifPresent(basket -> {
                    throw new BusinessException("there is already open basket for this client");
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


    public BasketEntity updateBasket(String basketId, BasketRequest basketRequest){
        BasketEntity savedBasket = getBasketById(basketId);


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

        savedBasket.setProducts(products);

        savedBasket.calculateTotalPrice();
        return basketRepository.save(savedBasket);
    }

    public BasketEntity payBasket(String basketId, PaymentMethodRequest request){
        BasketEntity savedBasket = getBasketById(basketId);

        savedBasket.setPaymentMethod(request.getPaymentMethod());
        savedBasket.setStatus(Status.SOLD);
        return basketRepository.save(savedBasket);
    }

    public void deleteBasket(String id){
        basketRepository.delete(getBasketById(id));
    }

}
