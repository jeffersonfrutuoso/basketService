package dev.java.ecommerce.basketService.controller;

import dev.java.ecommerce.basketService.controller.request.BasketRequest;
import dev.java.ecommerce.basketService.controller.request.PaymentMethodRequest;
import dev.java.ecommerce.basketService.controller.request.ProductRequest;
import dev.java.ecommerce.basketService.domain.BasketEntity;
import dev.java.ecommerce.basketService.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @PostMapping
    public ResponseEntity<BasketEntity> createBasket(@RequestBody BasketRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(basketService.createdBasket(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BasketEntity> updateBasket(@PathVariable String id, @RequestBody BasketRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(basketService.updateBasket(id,request));
    }

    @PutMapping("/{id}/payment")
    public ResponseEntity<BasketEntity> payBasket(@PathVariable String id, @RequestBody PaymentMethodRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(basketService.payBasket(id,request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasketEntity> getById(@PathVariable String id){
        return ResponseEntity.ok(basketService.getBasketById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletBasket(@PathVariable String id){
        basketService.deleteBasket(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
