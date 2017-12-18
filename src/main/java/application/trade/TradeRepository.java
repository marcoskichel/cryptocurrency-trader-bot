package application.trade;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends MongoRepository<Trade, String> {

    @Query("{ active: true }")
    List<Trade> findActive();

    @Query("{ active: true }")
    Long countActiveTrades();
}
