package application.market;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MarketRepository extends MongoRepository<Market, String> {

    Market findByNameLikeAndTimeStampBetween(String name, LocalDateTime start, LocalDateTime end);

}
