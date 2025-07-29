package dev.java.ecommerce.basketService.controller.request;


import dev.java.ecommerce.basketService.domain.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethodRequest{
    private PaymentMethod paymentMethod;
}
