package dev.java.ecommerce.basketService.controller;

import dev.java.ecommerce.basketService.controller.request.BasketRequest;
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

    @PostMapping("/criar")
    public ResponseEntity<BasketEntity> createBasket(@RequestBody BasketRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(basketService.createdBasket(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasketEntity> getById(@PathVariable String id){
        return ResponseEntity.ok(basketService.getBasketById(id));
    }


}
