package dev.java.ecommerce.basketService.reposiroty;

import dev.java.ecommerce.basketService.domain.BasketEntity;
import dev.java.ecommerce.basketService.domain.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface BasketRepository extends MongoRepository<BasketEntity, String> {

    Optional<BasketEntity> findByClientAndStatus(Long client, Status status);
}
