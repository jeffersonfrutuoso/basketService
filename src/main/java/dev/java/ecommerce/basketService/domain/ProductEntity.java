package dev.java.ecommerce.basketService.domain;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductEntity {
    private Long id;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
